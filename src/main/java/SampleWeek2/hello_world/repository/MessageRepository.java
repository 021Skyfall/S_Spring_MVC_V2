package SampleWeek2.hello_world.repository;

import SampleWeek2.hello_world.entity.Message;
import org.springframework.data.repository.CrudRepository;

//@Repository // 스프링이 알아서 해줘서 안 붙여도됨
public interface MessageRepository extends CrudRepository<Message,Long> {
}
