package utilities;

//@author Waqar Ahmed

public class LogUtil {
    /**
     * Whether to print logs, true means print logs, false means not print.
     * <p>
     * It can be set to ture in the development stage and can be set to false during the run phase.
     */

    private static final boolean enabled = true;
    /** Whether to carry out source code positioning, ture represents the source code source code and the line */
    private static boolean showLocSrc = true;
    /** Specified log level */
    private static int level = 1;
    /** Log level: Normal */
    private static final int info = 1;
    /** Log level: debugging */
    private static final int debug = 2;
    /** Log Level: Warning */
    private static final int warn = 3;
    /** Log Level: Error */
    private static final int error = 4;
    /** Delimiter of message content and message content */
    private static final String msgSplit = ":";
    /** The name of the class used to identify the stack of this class */
    private static final String thisClassName = LogUtil.class.getName();

    /** The default log output log tool: log4j */
    private static org.apache.log4j.Logger logger = null;

    public LogUtil() {
    }

    static {

        logger = org.apache.log4j.Logger.getLogger("");
    }

    /**
     * Test
     *
     * @param args
     */
    private static void main(String args[]) {
        LogUtil.info ("Debug Information");
    }

    /**
     * According to the log level, output the log.
     * <p>
     * If you want to change the log output tool,
     * <p>
     * Such as: from the original log4j to System.out.println () or logging, you only need to change this class.
     *
     * @param level
     * Log level
     * @param message
     * log messages
     * @param ste
     * Stack information.
     * <p>
     * If you do not need to output source code information, simply set the static member property showLocSrc to false.
     */
    private static void log(int level, Object message, StackTraceElement[] ste) {
        if (ste != null) {
// Add source code targeting
            message = getStackMsg(ste) + msgSplit + message;// fixme todo
        }
// Go to the concrete implementation, here log4j, can be changed to other different log implementations.
        switch (level) {
            case  info:
                logger.info(message);
                break;
            case  debug:
                logger.debug(message);
                break;
            case  warn:
                logger.warn(message);
                break;
            case  error:
                logger.error(message);
                break;
            default:
                logger.debug(message);
        }
    }

    /**
     * According to the stack information to get the source line information
     * <p>
     * Principle: The next line in the stack of this tool class is the original stack of source code.
     *
     * @param ste
     * Stack information
     * @return The class where the code that called the output log is located. Method. Information about the line of code
     * <p>
     * Such as: The fun() method in the com.MyClass class calls Logs.debug("test");
     * <p>
     * The stack information is: com.MyClass.fun (MyClass.java code line number)
     */
    private static String getStackMsg(StackTraceElement[] ste) {
        if (ste == null) {
            return null;
        }
        Boolean srcFlag = false;
        for (int i = 0; i < ste.length; i++) {
            StackTraceElement s = ste[i];
// If the previous line of stack code is a stack of this class, then that line of code is the original stack of source code.
            if (srcFlag) {
                return s == null ? "" : s.toString();
            }
// Locate the stack of this class
            if (thisClassName.equals(s.getClassName())) {
                srcFlag = true;
            }
        }
        return null;
    }

    /**
     * Output info info
     *
     * @param message
     */
    public static String info(Object message) {
// If the log is forbidden or the output level does not match, return.
        if (!enabled || info < level)
            return null;
        if (showLocSrc) {
            log(info, message, Thread.currentThread().getStackTrace());
        } else  {
            log(info, message, null);
        }
        return null;
    }

    /**
     * Output debug information
     *
     * @param message
     */
    public static void debug(Object message) {
// If the log is forbidden or the output level does not match, return.
        if (!enabled || debug < level)
            return;
        if (showLocSrc) {
            log(debug, message, Thread.currentThread().getStackTrace());
        } else  {
            log(debug, message, null);
        }
    }

    /**
     * Output warn information
     *
     * @param message
     */
    public static void warn(Object message) {
// If the log is forbidden or the output level does not match, return.
        if (!enabled || warn < level)
            return;
        if (showLocSrc) {
            log(warn, message, Thread.currentThread().getStackTrace());
        } else  {
            log(warn, message, null);
        }
    }

    /**
     * Output error information
     *
     * @param message
     */
    public static void error(Object message) {
// If the log is forbidden or the output level does not match, return.
        if (!enabled || error < level)
            return;
        if (showLocSrc) {
            log(error, message, Thread.currentThread().getStackTrace());
        } else  {
            log(error, message, null);
        }
    }


    public static String getFileAndLine() {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return  "[" + ste.getFileName() + ":" + ste.getLineNumber() + "]";
    }
}

