import com.chiyu.ssm.config.MybatisConfig;
import com.chiyu.ssm.config.SpringConfig;
import com.chiyu.ssm.entity.User;
import com.chiyu.ssm.mapper.UserMapper;
import com.chiyu.ssm.service.GoodsService;
import com.chiyu.ssm.service.UserService;
import com.chiyu.ssm.vo.SearchVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Test
    public void test01() {
        goodsService.selectGoodsByPageByParam(new SearchVo(),1,5);
    }
    @Test
    public void test() {
        User user = userService.selectUserByName("翠花");
        System.out.println(user);
    }

}
