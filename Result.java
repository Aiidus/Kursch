package sample;

public class Result {
    private String ResultTime;
    private int ResultInt;
    private int IdResult;
    private int IdTest;
    private String Login;

    public Result(String resultTime, int resultInt, int idTest, String login) {
        ResultTime = resultTime;
        ResultInt = resultInt;
        IdTest = idTest;
        Login = login;
    }
    public Result(String resultTime, int resultInt,  String login) {
        ResultTime = resultTime;
        ResultInt = resultInt;
        Login = login;
    }

    public String getResultTime() {
        return ResultTime;
    }

    public void setResultTime(String resultTime) {
        ResultTime = resultTime;
    }

    public int getResultInt() {
        return ResultInt;
    }

    public void setResultInt(int resultInt) {
        ResultInt = resultInt;
    }

    public int getIdResult() {
        return IdResult;
    }

    public void setIdResult(int idResult) {
        IdResult = idResult;
    }

    public int getIdTest() {
        return IdTest;
    }

    public void setIdTest(int idTest) {
        IdTest = idTest;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }
}
