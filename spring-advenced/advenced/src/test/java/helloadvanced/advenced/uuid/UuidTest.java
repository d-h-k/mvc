package helloadvanced.advenced.uuid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class UuidTest {

    Logger log = getLogger(this.getClass());

    @Test
    @DisplayName("UUID 테스트")
    public void test() {
        //given
        final int SAMPLE_COUNT = 100;
        List<UUID> uuidList = new ArrayList<>();


        //when
        for(int i=0; i<SAMPLE_COUNT ; i++) {
            uuidList.add(UUID.randomUUID());
        }


        //then
        assertThat(uuidList.size()).isEqualTo(SAMPLE_COUNT);
        // stream으로 중복을 제거하고난 뒤에도 값이 모두 다른지
        var distinctList = uuidList.stream().distinct().collect(Collectors.toList());
        assertThat(distinctList.size()).isEqualTo(SAMPLE_COUNT);
        // 로거로 찍어봐도 모든 값이 다르다
        distinctList.forEach(uuid -> log.info(uuid.toString()));
    }
}
