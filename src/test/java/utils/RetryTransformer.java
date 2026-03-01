package utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          Constructor testConstructor,
                          Method testMethod) {
        System.out.println("Transformer applied to: " + testMethod.getName());
        annotation.setRetryAnalyzer(RetryAnalyzer.class);  // 🔥 MUST EXIST

    }
}
