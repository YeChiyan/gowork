package mytest.shirodemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mytest.shirodemo.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
