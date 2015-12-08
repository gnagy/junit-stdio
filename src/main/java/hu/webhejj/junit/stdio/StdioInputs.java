package hu.webhejj.junit.stdio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Annotation for a class under test.</p>
 *
 * <p>The annotation expects a list of strings. For each value <code>$i</code> a file <code>input{$i}.txt</code>
 * will be piped to <code>STDIN</code> and the <code>STDOUT</code> will be compared to <code>output{$i}.txt</code>.
 * The files are expected to be on the CLASSPATH in the same package as the annotated test class.</p>
 *
 * <p>The class must also be annotated with &#064;RunWith({@link StdioSuite}.class).</p>
 *
 * <p>Example:</p>
 *
 * <pre>
 *  &#064;RunWith(StdioSuite.class)
 *  &#064;StdioInputs({"000", "001"})
 *  public class ToUpperTest {}
 *  </pre>
 *
 *  @see StdioSuite
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StdioInputs {
    String[] value();
}
