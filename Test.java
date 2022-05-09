package sample;

public class Test {
    public Test() {

    }

    public Test(String testName, String topicTest) {
        TestName = testName;
        TopicTest = topicTest;
    }

    @Override
    public String toString() {
        return "Test{" +
                "TestName='" + TestName + '\'' +
                ", TopicTest='" + TopicTest + '\'' +
                ", TestAuthor='" + TestAuthor + '\'' +
                ", TestText='" + TestText + '\'' +
                ", TestCol=" + TestCol +
                ", TestId=" + TestId +
                '}';
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getTopicTest() {
        return TopicTest;
    }

    public void setTopicTest(String topicTest) {
        TopicTest = topicTest;
    }

    public String getTestAuthor() {
        return TestAuthor;
    }

    public void setTestAuthor(String testAuthor) {
        TestAuthor = testAuthor;
    }

    public String getTestText() {
        return TestText;
    }

    public void setTestText(String testText) {
        TestText = testText;
    }

    public int getTestCol() {
        return TestCol;
    }

    public void setTestCol(int testCol) {
        TestCol = testCol;
    }

    private String TestName;
    private String TopicTest;
    private String TestAuthor;
    private String TestText;
    private int TestCol;
    private int TestId;

    public Test(int testId,String testName, String topicTest, String testAuthor, int testCol) {
        TestId=testId;
        TestName = testName;
        TopicTest = topicTest;
        TestAuthor = testAuthor;
        TestCol = testCol;

    }

    public Test(String testName, String topicTest, String testAuthor, String testText, int testCol) {
        TestName = testName;
        TopicTest = topicTest;
        TestAuthor = testAuthor;
        TestText = testText;
        TestCol = testCol;

    }

    public int getTestId() {
        return TestId;
    }

    public void setTestId(int testId) {
        TestId = testId;
    }
}
