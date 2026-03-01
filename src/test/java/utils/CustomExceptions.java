package utils;

public class CustomExceptions {

    // Base Exception
    public static class FrameworkException extends RuntimeException {
        public FrameworkException(String message) {
            super(message);
        }

        public FrameworkException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Element Not Found Exception
    public static class ElementNotFoundException extends FrameworkException {
        public ElementNotFoundException(String message) {
            super(message);
        }

        public ElementNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Element Not Clickable Exception
    public static class ElementNotClickableException extends FrameworkException {

        public ElementNotClickableException(String message) { super(message); }
        public ElementNotClickableException(String message, Throwable cause) { super(message, cause); } // ✅ add this
    }




// Invalid Test Data Exception
public static class InvalidTestDataException extends FrameworkException {
    public InvalidTestDataException(String message) { super(message); }
    public InvalidTestDataException(String message, Throwable cause) { super(message, cause); }     // ✅ optional but useful
}



    public static class ConfigurationChecked extends Exception {
        public ConfigurationChecked(String message) { super(message); }
        public ConfigurationChecked(String message, Throwable cause) { super(message, cause); }
    }
}

