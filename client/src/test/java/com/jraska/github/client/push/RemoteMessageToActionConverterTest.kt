package com.jraska.github.client.push

import com.jraska.github.client.common.Maps
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class RemoteMessageToActionConverterTest {
  @Test
  fun whenRemoteMessageWithoutAction_thenDefaultReturned() {
    val action = RemoteMessageToActionConverter.convert(Collections.emptyMap<String, String>())

    assertThat(action.name).isEqualTo(PushAction.DEFAULT.name)
  }

  @Test
  fun whenRemoteMessageWithAction_thenActionReturned() {
    val action = RemoteMessageToActionConverter.convert(Maps.newHashMap("action", "heeeya"))

    assertThat(action.name).isEqualTo("heeeya")
    assertThat(action.parameters).isEmpty()
  }

  @Test
  fun whenRemoteMessageWithParameters_thenActionReturned() {
    val map = HashMap<String, String>()
    map.put("action", "heeeya")
    map.put("parameter", "true")

    val action = RemoteMessageToActionConverter.convert(map)

    assertThat(action.name).isEqualTo("heeeya")
    assertThat(action.parameters).hasSize(1)
    assertThat(action.parameters["parameter"]).isEqualTo("true")
  }
}
