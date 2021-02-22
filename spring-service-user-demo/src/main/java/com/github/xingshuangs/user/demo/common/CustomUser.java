package com.github.xingshuangs.user.demo.common;


import com.github.xingshuangs.user.demo.utils.BeanUtils;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @author xingshuang
 * @date 2020/5/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomUser extends User implements Serializable {

    private Integer userId = 1;

    private Integer tenantId = 33;

    private String realName = "xingshuang";

    private String mobile = "18767111111";

    private String email = "xingshuang_cool@163.com";

    private String gender = "男";

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    /**
     * 将本地中的用户信息转换为map，用户数据加强时使用
     *
     * @return map对象
     */
    public Map<String, Object> convert2Map() throws IllegalAccessException {
        Map<String, Object> map = Maps.newHashMap();
        map.putAll(BeanUtils.field2MapExcludeParent(this));
        map.put("username", this.getUsername());
        map.put("authorities", AuthorityUtils.authorityListToSet(this.getAuthorities()));
        return map;
    }
}
