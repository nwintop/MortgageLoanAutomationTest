package Base;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class MyListener implements ITestListener {
	

@Override
public void onFinish(ITestContext contextFinish) {
	System.out.println("I am in onFinish method " + contextFinish.getName());
	
}

@Override
public void onStart(ITestContext iTestContext) {
	System.out.println("I am in onStart method " + iTestContext.getName());
	
}

@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
System.out.println("Method failed with certain success percentage"+ result.getName());

}

@Override
public void onTestFailure(ITestResult iTestResult) {
   

}

@Override
public void onTestSkipped(ITestResult result) {
System.out.println("Method skipped"+ result.getName());

}

@Override
public void onTestStart(ITestResult result) {
System.out.println("Method started"+ result.getName());

}

@Override
public void onTestSuccess(ITestResult result) {
System.out.println("Method passed"+ result.getName());


}

}
