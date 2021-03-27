package fans.umamusume.www.common.bo;

public class SimulateCalcRes {

    public int totalStatus;
    public int realSpeed;
    public int realStamina;
    public int realPow;
    public int realGuts;
    public int realWiz;
    public float hp;

    public float raceStandardSpeed;
    public float wizSpeedCoef;
    public float standardTargetSpeed;
    public float targetSpeed;
    public float minTargetSpeed;
    public float maxTargetSpeed;
    public float currentSpeed;
    public float currentSpurtSpeed;
    public float hpDecRPS;
    public float hpSpurtDecRPS;
    public float gutsHpDecCoef;
    public float a;

    public float startSpeed;
    public float middleSpeed;
    public float endSpeed;
    public float spurtSpeed;
    public float startA;
    public float middleA;
    public float endA;
    public float spurtA;

    public float totalTime;
    public float startAccTime;
    public float startRemainTime;
    public float startAccDistance;
    public float middleAccTime;
    public float middleRemainTime;
    public float middleAccDistance;
    public float endAccTime;
    public float endRemainTime;
    public float endAccDistance;
    public float spurtAccTime;
    public float spurtRemainTime;
    public float spurtAccDistance;

    public float totalHpDec;
    public float startHpDec;
    public float middleHpDec;
    public float endHpDec;
    public float spurtHpDec;

    public SimulateCalcRes(int totalStatus, int realSpeed, int realStamina, int realPow, int realGuts, int realWiz, float hp, float raceStandardSpeed, float wizSpeedCoef, float standardTargetSpeed, float targetSpeed, float minTargetSpeed, float maxTargetSpeed, float currentSpeed, float currentSpurtSpeed, float hpDecRPS, float hpSpurtDecRPS, float gutsHpDecCoef, float a, float startSpeed, float middleSpeed, float endSpeed, float spurtSpeed, float startA, float middleA, float endA, float spurtA, float totalTime, float startAccTime, float startRemainTime, float startAccDistance, float middleAccTime, float middleRemainTime, float middleAccDistance, float endAccTime, float endRemainTime, float endAccDistance, float spurtAccTime, float spurtRemainTime, float spurtAccDistance, float totalHpDec, float startHpDec, float middleHpDec, float endHpDec, float spurtHpDec) {
        this.totalStatus = totalStatus;
        this.realSpeed = realSpeed;
        this.realStamina = realStamina;
        this.realPow = realPow;
        this.realGuts = realGuts;
        this.realWiz = realWiz;
        this.hp = hp;
        this.raceStandardSpeed = raceStandardSpeed;
        this.wizSpeedCoef = wizSpeedCoef;
        this.standardTargetSpeed = standardTargetSpeed;
        this.targetSpeed = targetSpeed;
        this.minTargetSpeed = minTargetSpeed;
        this.maxTargetSpeed = maxTargetSpeed;
        this.currentSpeed = currentSpeed;
        this.currentSpurtSpeed = currentSpurtSpeed;
        this.hpDecRPS = hpDecRPS;
        this.hpSpurtDecRPS = hpSpurtDecRPS;
        this.gutsHpDecCoef = gutsHpDecCoef;
        this.a = a;
        this.startSpeed = startSpeed;
        this.middleSpeed = middleSpeed;
        this.endSpeed = endSpeed;
        this.spurtSpeed = spurtSpeed;
        this.startA = startA;
        this.middleA = middleA;
        this.endA = endA;
        this.spurtA = spurtA;
        this.totalTime = totalTime;
        this.startAccTime = startAccTime;
        this.startRemainTime = startRemainTime;
        this.startAccDistance = startAccDistance;
        this.middleAccTime = middleAccTime;
        this.middleRemainTime = middleRemainTime;
        this.middleAccDistance = middleAccDistance;
        this.endAccTime = endAccTime;
        this.endRemainTime = endRemainTime;
        this.endAccDistance = endAccDistance;
        this.spurtAccTime = spurtAccTime;
        this.spurtRemainTime = spurtRemainTime;
        this.spurtAccDistance = spurtAccDistance;
        this.totalHpDec = totalHpDec;
        this.startHpDec = startHpDec;
        this.middleHpDec = middleHpDec;
        this.endHpDec = endHpDec;
        this.spurtHpDec = spurtHpDec;
    }

    public SimulateCalcRes() {
    }

    public int getTotalStatus() {
        return totalStatus;
    }

    public void setTotalStatus(int totalStatus) {
        this.totalStatus = totalStatus;
    }

    public int getRealSpeed() {
        return realSpeed;
    }

    public void setRealSpeed(int realSpeed) {
        this.realSpeed = realSpeed;
    }

    public int getRealStamina() {
        return realStamina;
    }

    public void setRealStamina(int realStamina) {
        this.realStamina = realStamina;
    }

    public int getRealPow() {
        return realPow;
    }

    public void setRealPow(int realPow) {
        this.realPow = realPow;
    }

    public int getRealGuts() {
        return realGuts;
    }

    public void setRealGuts(int realGuts) {
        this.realGuts = realGuts;
    }

    public int getRealWiz() {
        return realWiz;
    }

    public void setRealWiz(int realWiz) {
        this.realWiz = realWiz;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getRaceStandardSpeed() {
        return raceStandardSpeed;
    }

    public void setRaceStandardSpeed(float raceStandardSpeed) {
        this.raceStandardSpeed = raceStandardSpeed;
    }

    public float getWizSpeedCoef() {
        return wizSpeedCoef;
    }

    public void setWizSpeedCoef(float wizSpeedCoef) {
        this.wizSpeedCoef = wizSpeedCoef;
    }

    public float getStandardTargetSpeed() {
        return standardTargetSpeed;
    }

    public void setStandardTargetSpeed(float standardTargetSpeed) {
        this.standardTargetSpeed = standardTargetSpeed;
    }

    public float getTargetSpeed() {
        return targetSpeed;
    }

    public void setTargetSpeed(float targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    public float getMinTargetSpeed() {
        return minTargetSpeed;
    }

    public void setMinTargetSpeed(float minTargetSpeed) {
        this.minTargetSpeed = minTargetSpeed;
    }

    public float getMaxTargetSpeed() {
        return maxTargetSpeed;
    }

    public void setMaxTargetSpeed(float maxTargetSpeed) {
        this.maxTargetSpeed = maxTargetSpeed;
    }

    public float getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public float getCurrentSpurtSpeed() {
        return currentSpurtSpeed;
    }

    public void setCurrentSpurtSpeed(float currentSpurtSpeed) {
        this.currentSpurtSpeed = currentSpurtSpeed;
    }

    public float getHpDecRPS() {
        return hpDecRPS;
    }

    public void setHpDecRPS(float hpDecRPS) {
        this.hpDecRPS = hpDecRPS;
    }

    public float getHpSpurtDecRPS() {
        return hpSpurtDecRPS;
    }

    public void setHpSpurtDecRPS(float hpSpurtDecRPS) {
        this.hpSpurtDecRPS = hpSpurtDecRPS;
    }

    public float getGutsHpDecCoef() {
        return gutsHpDecCoef;
    }

    public void setGutsHpDecCoef(float gutsHpDecCoef) {
        this.gutsHpDecCoef = gutsHpDecCoef;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getStartSpeed() {
        return startSpeed;
    }

    public void setStartSpeed(float startSpeed) {
        this.startSpeed = startSpeed;
    }

    public float getMiddleSpeed() {
        return middleSpeed;
    }

    public void setMiddleSpeed(float middleSpeed) {
        this.middleSpeed = middleSpeed;
    }

    public float getEndSpeed() {
        return endSpeed;
    }

    public void setEndSpeed(float endSpeed) {
        this.endSpeed = endSpeed;
    }

    public float getSpurtSpeed() {
        return spurtSpeed;
    }

    public void setSpurtSpeed(float spurtSpeed) {
        this.spurtSpeed = spurtSpeed;
    }

    public float getStartA() {
        return startA;
    }

    public void setStartA(float startA) {
        this.startA = startA;
    }

    public float getMiddleA() {
        return middleA;
    }

    public void setMiddleA(float middleA) {
        this.middleA = middleA;
    }

    public float getEndA() {
        return endA;
    }

    public void setEndA(float endA) {
        this.endA = endA;
    }

    public float getSpurtA() {
        return spurtA;
    }

    public void setSpurtA(float spurtA) {
        this.spurtA = spurtA;
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(float totalTime) {
        this.totalTime = totalTime;
    }

    public float getStartAccTime() {
        return startAccTime;
    }

    public void setStartAccTime(float startAccTime) {
        this.startAccTime = startAccTime;
    }

    public float getStartRemainTime() {
        return startRemainTime;
    }

    public void setStartRemainTime(float startRemainTime) {
        this.startRemainTime = startRemainTime;
    }

    public float getStartAccDistance() {
        return startAccDistance;
    }

    public void setStartAccDistance(float startAccDistance) {
        this.startAccDistance = startAccDistance;
    }

    public float getMiddleAccTime() {
        return middleAccTime;
    }

    public void setMiddleAccTime(float middleAccTime) {
        this.middleAccTime = middleAccTime;
    }

    public float getMiddleRemainTime() {
        return middleRemainTime;
    }

    public void setMiddleRemainTime(float middleRemainTime) {
        this.middleRemainTime = middleRemainTime;
    }

    public float getMiddleAccDistance() {
        return middleAccDistance;
    }

    public void setMiddleAccDistance(float middleAccDistance) {
        this.middleAccDistance = middleAccDistance;
    }

    public float getEndAccTime() {
        return endAccTime;
    }

    public void setEndAccTime(float endAccTime) {
        this.endAccTime = endAccTime;
    }

    public float getEndRemainTime() {
        return endRemainTime;
    }

    public void setEndRemainTime(float endRemainTime) {
        this.endRemainTime = endRemainTime;
    }

    public float getEndAccDistance() {
        return endAccDistance;
    }

    public void setEndAccDistance(float endAccDistance) {
        this.endAccDistance = endAccDistance;
    }

    public float getSpurtAccTime() {
        return spurtAccTime;
    }

    public void setSpurtAccTime(float spurtAccTime) {
        this.spurtAccTime = spurtAccTime;
    }

    public float getSpurtRemainTime() {
        return spurtRemainTime;
    }

    public void setSpurtRemainTime(float spurtRemainTime) {
        this.spurtRemainTime = spurtRemainTime;
    }

    public float getSpurtAccDistance() {
        return spurtAccDistance;
    }

    public void setSpurtAccDistance(float spurtAccDistance) {
        this.spurtAccDistance = spurtAccDistance;
    }

    public float getTotalHpDec() {
        return totalHpDec;
    }

    public void setTotalHpDec(float totalHpDec) {
        this.totalHpDec = totalHpDec;
    }

    public float getStartHpDec() {
        return startHpDec;
    }

    public void setStartHpDec(float startHpDec) {
        this.startHpDec = startHpDec;
    }

    public float getMiddleHpDec() {
        return middleHpDec;
    }

    public void setMiddleHpDec(float middleHpDec) {
        this.middleHpDec = middleHpDec;
    }

    public float getEndHpDec() {
        return endHpDec;
    }

    public void setEndHpDec(float endHpDec) {
        this.endHpDec = endHpDec;
    }

    public float getSpurtHpDec() {
        return spurtHpDec;
    }

    public void setSpurtHpDec(float spurtHpDec) {
        this.spurtHpDec = spurtHpDec;
    }
}
