package subway.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StationRepositoryTest {

    @Autowired
    private StationRepository stations;

    @Test
    void save() {
        final Station station = new Station("잠실역");
        final Station actual = stations.save(station);
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo("잠실역");
    }

    @Test
    void findByName() {
        final Station station1 = stations.save(new Station("잠실역"));
        final Station station2 = stations.findByName("잠실역");
        assertThat(station2.getId()).isEqualTo(station1.getId());
        assertThat(station2.getName()).isEqualTo(station1.getName());
        assertThat(station2).isEqualTo(station1);
        assertThat(station2).isSameAs(station1);
    }
}
