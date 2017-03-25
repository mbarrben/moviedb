import java.util.logging.Level
import java.util.logging.Logger

final class DefaultProperties extends Properties {

  private final static String DEFAULT_VALUE = ""
  private final static String DEFAULT_FILE_NAME = 'private.properties'

  private final Object defaultValue

  DefaultProperties(Object defaultValue) {
    this.defaultValue = defaultValue
  }

  @Override synchronized Object get(Object o) {
    return super.get(o, defaultValue)
  }

  @Override String getProperty(String s) {
    String result = super.getProperty(s)
    return check(result, defaultValue)
  }

  static Properties create(Map args) {
    args.defaultValue = check(args.defaultValue, DEFAULT_VALUE)
    args.fileName = check(args.fileName, DEFAULT_FILE_NAME)

    Properties properties = new DefaultProperties("${args.defaultValue}")
    try {
      properties.load(new FileInputStream("${args.fileName}"))
    } catch (FileNotFoundException e) {
      Logger.getLogger("BuildConfig").log(Level.SEVERE, "Can't find ${args.fileName} file, will fallback to empty properties", e)
    }

    return properties
  }

  static Properties create(String defaultValue = DEFAULT_VALUE, String fileName = DEFAULT_FILE_NAME) {
    create(defaultValue: defaultValue, fileName: fileName)
  }

  private static Object check(Object value, Object defaultValue) {
    return value != null ? value : defaultValue
  }
}
