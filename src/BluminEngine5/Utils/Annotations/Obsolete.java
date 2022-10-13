package BluminEngine5.Utils.Annotations;


import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;



/**
 * {@summary Is used to show that a function or class is no longer in use and should be avoided}
 * @author Vrglab
 * @since 0.0.1.0_Dev
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE})
public @interface Obsolete {
        String message() default "";
}

