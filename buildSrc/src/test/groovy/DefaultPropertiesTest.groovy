import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat;

class DefaultPropertiesTest {

  @Test void testFileDoesNotExist() {
    Properties properties = DefaultProperties.create(fileName: 'nonexistingfile')
    def apiKey = properties['API_KEY']
    assertThat(apiKey).isEqualTo("")
  }

  @Test void testDefaultValueIsSet() {
    Properties properties = DefaultProperties.create(defaultValue: 'fake', fileName: 'nonexistingfile')
    def apiKey = properties['API_KEY']
    assertThat(apiKey).isEqualTo('fake')
  }

  @Test void testDefaultFileContainsApiKey() {
    Properties properties = DefaultProperties.create()
    def apiKey = properties['API_KEY']
    assertThat(apiKey).isEqualTo('fake_key')
  }

  @Test void testDefaultFileContainsReadAccessToken() {
    Properties properties = DefaultProperties.create()
    def token = properties['API_READ_ACCESS_TOKEN']
    assertThat(token).isEqualTo('fake_token')
  }
}