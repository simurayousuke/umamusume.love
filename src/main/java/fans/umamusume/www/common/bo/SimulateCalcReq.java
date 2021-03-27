package fans.umamusume.www.common.bo;

public class SimulateCalcReq {

    private int speed;
    private int stamina;
    private int pow;
    private int guts;
    private int wiz;

    // 1草地，2泥地
    private int ground;
    // 1良，2稍重，3重，4不良
    private int groundType;
    // 1逃，2先行，3差，4追
    private int runningStyle;

    private float motivationCoef;
    private float properRunningStyleCoef;
    private float properDistanceCoef;
    private float properGroundTypeCoef;

    private int distance;

    private float baseSpeedCoef = 1f;
    private float frontBlockSpeed = 20f;
    private float hpDecRate = 1f;
    private float startRate = 0.2f;
    private float middleRate = 0.4f;
    private float endRate = 0.2f;
    private float spurtRate = 0.2f;

    public SimulateCalcReq(int speed, int stamina, int pow, int guts, int wiz, int ground, int groundType, int runningStyle, float motivationCoef, float properRunningStyleCoef, float properDistanceCoef, float properGroundTypeCoef, int distance, float baseSpeedCoef, float frontBlockSpeed, float hpDecRate, float startRate, float middleRate, float endRate, float spurtRate) {
        this.speed = speed;
        this.stamina = stamina;
        this.pow = pow;
        this.guts = guts;
        this.wiz = wiz;
        this.ground = ground;
        this.groundType = groundType;
        this.runningStyle = runningStyle;
        this.motivationCoef = motivationCoef;
        this.properRunningStyleCoef = properRunningStyleCoef;
        this.properDistanceCoef = properDistanceCoef;
        this.properGroundTypeCoef = properGroundTypeCoef;
        this.distance = distance;
        this.baseSpeedCoef = baseSpeedCoef;
        this.frontBlockSpeed = frontBlockSpeed;
        this.hpDecRate = hpDecRate;
        this.startRate = startRate;
        this.middleRate = middleRate;
        this.endRate = endRate;
        this.spurtRate = spurtRate;
    }

    public SimulateCalcReq() {
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getPow() {
        return pow;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }

    public int getGuts() {
        return guts;
    }

    public void setGuts(int guts) {
        this.guts = guts;
    }

    public int getWiz() {
        return wiz;
    }

    public void setWiz(int wiz) {
        this.wiz = wiz;
    }

    public int getGround() {
        return ground;
    }

    public void setGround(int ground) {
        this.ground = ground;
    }

    public int getGroundType() {
        return groundType;
    }

    public void setGroundType(int groundType) {
        this.groundType = groundType;
    }

    public int getRunningStyle() {
        return runningStyle;
    }

    public void setRunningStyle(int runningStyle) {
        this.runningStyle = runningStyle;
    }

    public float getMotivationCoef() {
        return motivationCoef;
    }

    public void setMotivationCoef(float motivationCoef) {
        this.motivationCoef = motivationCoef;
    }

    public float getProperRunningStyleCoef() {
        return properRunningStyleCoef;
    }

    public void setProperRunningStyleCoef(float properRunningStyleCoef) {
        this.properRunningStyleCoef = properRunningStyleCoef;
    }

    public float getProperDistanceCoef() {
        return properDistanceCoef;
    }

    public void setProperDistanceCoef(float properDistanceCoef) {
        this.properDistanceCoef = properDistanceCoef;
    }

    public float getProperGroundTypeCoef() {
        return properGroundTypeCoef;
    }

    public void setProperGroundTypeCoef(float properGroundTypeCoef) {
        this.properGroundTypeCoef = properGroundTypeCoef;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getBaseSpeedCoef() {
        return baseSpeedCoef;
    }

    public void setBaseSpeedCoef(float baseSpeedCoef) {
        this.baseSpeedCoef = baseSpeedCoef;
    }

    public float getFrontBlockSpeed() {
        return frontBlockSpeed;
    }

    public void setFrontBlockSpeed(float frontBlockSpeed) {
        this.frontBlockSpeed = frontBlockSpeed;
    }

    public float getHpDecRate() {
        return hpDecRate;
    }

    public void setHpDecRate(float hpDecRate) {
        this.hpDecRate = hpDecRate;
    }

    public float getStartRate() {
        return startRate;
    }

    public void setStartRate(float startRate) {
        this.startRate = startRate;
    }

    public float getMiddleRate() {
        return middleRate;
    }

    public void setMiddleRate(float middleRate) {
        this.middleRate = middleRate;
    }

    public float getEndRate() {
        return endRate;
    }

    public void setEndRate(float endRate) {
        this.endRate = endRate;
    }

    public float getSpurtRate() {
        return spurtRate;
    }

    public void setSpurtRate(float spurtRate) {
        this.spurtRate = spurtRate;
    }
}
