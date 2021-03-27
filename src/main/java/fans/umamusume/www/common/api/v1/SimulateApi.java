package fans.umamusume.www.common.api.v1;

import com.jfinal.kit.Ret;
import fans.umamusume.www.common.base.ApiV1;
import fans.umamusume.www.common.bo.SimulateCalcReq;
import fans.umamusume.www.common.bo.SimulateCalcRes;

public class SimulateApi extends ApiV1 {

    public static final float BaseTargetSpeedRandomMinusVal1 = -0.65f;
    public static final float BaseTargetSpeedRandomMinusVal2 = 5500f;
    public static final float BaseTargetSpeedRandomPlusVal1 = 5500f;
    public static final float HpInitialVal1 = 0.8f;
    public static final float PhaseEndBaseTargetSpeedCoef = 500f;
    public static final float addSpeedParamCoef = 0.002f;
    public static final float MinSpeedRate = 0.85f;
    public static final float MinSpeedGutsCoefSqrt = 200f;
    public static final float MinSpeedGutsCoef = 0.001f;
    public static final float baseTargetSpeedCoef = 1.05f;
    public static final float lastSpurtBaseTargetSpeedAddCoef = 0.01f;
    public static final float LastSpurtTargetSpeedCoefSqrt = 500f;
    public static final float SpeedGapParam1 = 12f;
    public static final float SpeedGapParam1Pow = 144f;
    public static final float HpGutsCoef = 200f;
    public static final float HpGutsCoefSqrt = 600f;
    public static final float HpDecBase = 20f;
    public static final float accelPowCoef = 0.0006f;
    public static final float accelPowCoefSqrt = 500f;

    public static final float[][] runningStyleSpeedCoefTable = new float[][]{
            {1f, 0.98f, 0.962f, 0.962f},
            {0.978f, 0.991f, 0.975f, 0.975f},
            {0.938f, 0.998f, 0.994f, 0.994f},
            {0.931f, 1f, 1f, 1f}
    };

    public static final float[][] runningStyleACoefTable = new float[][]{
            {1f, 1f, 0.996f, 0.996f},
            {0.985f, 1f, 0.996f, 0.996f},
            {0.975f, 1f, 1f, 1f},
            {0.945f, 1f, 0.997f, 0.997f}
    };

    public static final float[][] groundTypeAddParaTable = new float[][]{
            {0f, 0f},
            {0f, -50f},
            {0f, -50f},
            {-50f, -50f}
    };

    public static final float[] runningStyleHpCoefTable = new float[]{
            0.95f, 0.89f, 1f, 0.995f
    };

    public static final float[][] groundHpDecCoefTable = new float[][]{
            {1f, 1f, 1.02f, 1.02f},
            {1f, 1f, 1.01f, 1.02f}
    };

    public void calculate() {
        try {
            SimulateCalcReq req = getBean(SimulateCalcReq.class, "");
            if (null == req) {
                renderJson(Ret.fail());
                return;
            }
            SimulateCalcRes res = new SimulateCalcRes();
            res.realSpeed = (int) (req.getSpeed() * req.getMotivationCoef() * req.getBaseSpeedCoef() + groundTypeAddParaTable[req.getGroundType() - 1][0]);
            res.realStamina = (int) (req.getStamina() * req.getMotivationCoef());
            res.realPow = (int) (req.getPow() * req.getMotivationCoef() + groundTypeAddParaTable[req.getGroundType() - 1][1]);
            res.realGuts = (int) (req.getGuts() * req.getMotivationCoef());
            res.realWiz = (int) (req.getWiz() * req.getMotivationCoef() * req.getProperRunningStyleCoef());
            res.totalStatus = req.getSpeed() + req.getStamina() + req.getPow() + req.getGuts() + req.getWiz();
            res.hp = req.getDistance() + runningStyleHpCoefTable[req.getRunningStyle() - 1] * HpInitialVal1 * res.realStamina;
            res.raceStandardSpeed = 20f + (2000f - req.getDistance()) / 1000f;
            double minWizSpeedCoef = BaseTargetSpeedRandomMinusVal1 + (res.realWiz / BaseTargetSpeedRandomMinusVal2) * Math.log(res.realWiz * 0.1f);
            double maxWizSpeedCoef = (res.realWiz / BaseTargetSpeedRandomPlusVal1) * Math.log(res.realWiz * 0.1);
            res.wizSpeedCoef = (float) ((maxWizSpeedCoef + minWizSpeedCoef) / 2f);
            res.standardTargetSpeed = (float) (Math.sqrt(PhaseEndBaseTargetSpeedCoef * res.realSpeed) * req.getProperDistanceCoef() * addSpeedParamCoef
                    + res.raceStandardSpeed * (req.getProperRunningStyleCoef() + res.wizSpeedCoef / 100f));
            res.targetSpeed = res.standardTargetSpeed;//简化
            res.minTargetSpeed = (float) (res.raceStandardSpeed * MinSpeedRate + Math.sqrt(res.realGuts * MinSpeedGutsCoefSqrt) * MinSpeedGutsCoef);
            res.maxTargetSpeed = Math.max(res.minTargetSpeed, Math.min(30f, req.getFrontBlockSpeed()));//有简化
            res.currentSpeed = Math.min(res.maxTargetSpeed, Math.max(res.minTargetSpeed, res.targetSpeed));
            res.currentSpurtSpeed = (float) (baseTargetSpeedCoef * (res.standardTargetSpeed + lastSpurtBaseTargetSpeedAddCoef * res.raceStandardSpeed)
                    + Math.sqrt(LastSpurtTargetSpeedCoefSqrt * res.realSpeed) * req.getProperDistanceCoef() * addSpeedParamCoef);
            res.hpDecRPS = HpDecBase * req.getHpDecRate() * (res.currentSpeed - res.raceStandardSpeed + SpeedGapParam1) * (res.currentSpeed - res.raceStandardSpeed + SpeedGapParam1)
                    / SpeedGapParam1Pow * groundHpDecCoefTable[req.getGround() - 1][req.getGroundType() - 1];
            res.gutsHpDecCoef = (float) (HpGutsCoef / Math.sqrt(HpGutsCoefSqrt * res.realGuts) + 1);
            res.hpSpurtDecRPS = HpDecBase * req.getHpDecRate() * (res.currentSpurtSpeed - res.raceStandardSpeed + SpeedGapParam1) * (res.currentSpurtSpeed - res.raceStandardSpeed + SpeedGapParam1)
                    / SpeedGapParam1Pow * groundHpDecCoefTable[req.getGround() - 1][req.getGroundType() - 1] * res.gutsHpDecCoef;
            res.a = (float) (accelPowCoef * Math.sqrt(res.getRealPow() * accelPowCoefSqrt) * req.getProperGroundTypeCoef());

            res.startSpeed = res.currentSpeed * runningStyleSpeedCoefTable[req.getRunningStyle() - 1][0];
            res.middleSpeed = res.currentSpeed * runningStyleSpeedCoefTable[req.getRunningStyle() - 1][1];
            res.endSpeed = res.currentSpeed * runningStyleSpeedCoefTable[req.getRunningStyle() - 1][2];
            res.spurtSpeed = res.currentSpurtSpeed * runningStyleSpeedCoefTable[req.getRunningStyle() - 1][3];
            res.startA = res.a * runningStyleACoefTable[req.getRunningStyle() - 1][0];
            res.middleA = res.a * runningStyleACoefTable[req.getRunningStyle() - 1][1];
            res.endA = res.a * runningStyleACoefTable[req.getRunningStyle() - 1][2];
            res.spurtA = res.a * runningStyleACoefTable[req.getRunningStyle() - 1][3];

            float tempA;
            res.startAccDistance = res.startSpeed * res.startSpeed / 2f / (res.startA + 24f);
            res.startAccTime = res.startSpeed / (res.startA + 24f);
            res.startRemainTime = (req.getDistance() * req.getStartRate() - res.startAccDistance) / res.startSpeed;

            tempA = res.middleSpeed - res.startSpeed < 0 ? 0.8f : res.middleA;
            res.middleAccDistance = Math.abs(res.middleSpeed * res.middleSpeed - res.startSpeed * res.startSpeed) / 2f / tempA;
            res.middleAccTime = Math.abs(res.middleSpeed - res.startSpeed) / tempA;
            res.middleRemainTime = (req.getDistance() * req.getMiddleRate() - res.middleAccDistance) / res.middleSpeed;

            tempA = res.endSpeed - res.middleSpeed < 0 ? 1.0f : res.middleA;
            res.endAccDistance = Math.abs(res.middleSpeed * res.middleSpeed - res.endSpeed * res.endSpeed) / 2f / tempA;
            res.endAccTime = Math.abs(res.middleSpeed - res.endSpeed) / tempA;
            res.endRemainTime = (req.getDistance() * req.getEndRate() - res.endAccDistance) / res.endSpeed;

            res.spurtAccDistance = (res.spurtSpeed * res.spurtSpeed - res.endSpeed * res.endSpeed) / 2f / tempA;
            res.spurtAccTime = (res.spurtSpeed - res.endSpeed) / 2f / res.spurtA;
            res.spurtRemainTime = (req.getDistance() * req.getSpurtRate() - res.spurtAccDistance) / res.spurtSpeed;

            res.totalTime = res.startAccTime + res.startRemainTime + res.middleAccTime + res.middleRemainTime
                    + res.endAccTime + res.endRemainTime + res.spurtAccTime + res.spurtRemainTime;

            res.startHpDec = HpDecBase * req.getHpDecRate() * (res.startSpeed - res.raceStandardSpeed + SpeedGapParam1) * (res.startSpeed - res.raceStandardSpeed + SpeedGapParam1)
                    / SpeedGapParam1Pow * groundHpDecCoefTable[req.getGround() - 1][req.getGroundType() - 1] * (res.startAccTime + res.startRemainTime);
            res.middleHpDec = HpDecBase * req.getHpDecRate() * (res.middleSpeed - res.raceStandardSpeed + SpeedGapParam1) * (res.middleSpeed - res.raceStandardSpeed + SpeedGapParam1)
                    / SpeedGapParam1Pow * groundHpDecCoefTable[req.getGround() - 1][req.getGroundType() - 1] * (res.middleAccTime + res.middleRemainTime);
            res.endHpDec = HpDecBase * req.getHpDecRate() * (res.endSpeed - res.raceStandardSpeed + SpeedGapParam1) * (res.endSpeed - res.raceStandardSpeed + SpeedGapParam1)
                    / SpeedGapParam1Pow * groundHpDecCoefTable[req.getGround() - 1][req.getGroundType() - 1] * (res.endAccTime + res.endRemainTime);
            res.spurtHpDec = HpDecBase * req.getHpDecRate() * (res.spurtSpeed - res.raceStandardSpeed + SpeedGapParam1) * (res.spurtSpeed - res.raceStandardSpeed + SpeedGapParam1)
                    / SpeedGapParam1Pow * groundHpDecCoefTable[req.getGround() - 1][req.getGroundType() - 1] * (res.spurtAccTime + res.spurtRemainTime) * res.gutsHpDecCoef;
            res.totalHpDec = res.startHpDec + res.middleHpDec + res.endHpDec + res.spurtHpDec;
            renderJson(Ret.ok("data", res));
        } catch (Exception e) {
            renderJson(Ret.fail("msg", "500"));
        }
    }

}
