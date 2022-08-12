package lazy.service;

import lazy.repo.LazyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.08.09
 */
@Component
public class LazyServiceImpl implements LazyService {

    LazyServiceImpl(@Autowired(required=false) LazyRepo lazyRepo){
       System.out.println(lazyRepo);
   }

}
