import org.gradle.api.tasks.wrapper.Wrapper;

import static org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL;

public class CustomWrapper extends Wrapper {

  public CustomWrapper() {
    super();
    setDistributionType(ALL);
  }
}
