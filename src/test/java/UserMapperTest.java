import com.chiyu.ssm.config.MybatisConfig;
import com.chiyu.ssm.config.SpringConfig;
import com.chiyu.ssm.entity.Goods;
import com.chiyu.ssm.entity.User;
import com.chiyu.ssm.mapper.UserMapper;
import com.chiyu.ssm.service.GoodsService;
import com.chiyu.ssm.service.UserService;
import com.chiyu.ssm.vo.PageVo;
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
        SearchVo searchVo = new SearchVo();
        searchVo.setGname("娃");
        PageVo<Goods> goodsPageVo = goodsService.selectGoodsByPageByParam(searchVo, 1, 5);
        System.out.println("goodsPageVo = " + goodsPageVo);
    }
    @Test
    public void test() {
        User user = userService.selectUserByName("翠花");
        System.out.println(user);
    }

}
