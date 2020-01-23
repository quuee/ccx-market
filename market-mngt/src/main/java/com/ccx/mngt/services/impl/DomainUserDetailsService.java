package com.ccx.mngt.services.impl;


import com.ccx.entity.sys.MenuDO;
import com.ccx.entity.sys.RoleDO;
import com.ccx.entity.sys.UserDO;
import com.ccx.mngt.services.UserService;
import com.ccx.mngt.commons.dto.MenuRouteButtonVO;
import com.ccx.mngt.commons.dto.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//@Component("domainUserDetailService")
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DomainUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDO userEntity = userService.queryUserByUsername(username);
        if(userEntity==null){
            logger.error("找不到用户：{}",username);
            throw new UsernameNotFoundException("用户" + username + "不存在!");
        }else{
            Set<GrantedAuthority> authorities=new HashSet<>();
            if(userEntity.getRoleSet()!=null || !userEntity.getRoleSet().isEmpty()){
                for (RoleDO role : userEntity.getRoleSet()) {
                    for (MenuDO menuEntity : role.getMenuSet()) {
                        authorities.add(new SimpleGrantedAuthority(menuEntity.getPermission()));
                    }
                }
            }
            Set<MenuRouteButtonVO> menuRouteButtonVO = compose(userEntity.getRoleSet());
            return new MyUserDetails(userEntity.getUsername(),
                    userEntity.getPassword(),
                    authorities,
                    menuRouteButtonVO,
                    userEntity.getId(),
                    userEntity.getSuperUser());
        }

    }

    /**
     * authorities 是把所有的菜单权限以列表的形式返回，
     * 前端需要数据嵌套组合的方式构建页面的权限
     * @param roleDOS
     * @return
     */
    private Set<MenuRouteButtonVO> compose(Set<RoleDO> roleDOS){
        Set<MenuRouteButtonVO> menuRouteButtonVOSet = new HashSet<>();
        if(roleDOS==null || roleDOS.isEmpty()){
            return menuRouteButtonVOSet;
        }
        for (RoleDO roleDO : roleDOS) {
            Set<MenuDO> menuSet = roleDO.getMenuSet();
            Map<Integer, List<MenuDO>> collect = menuSet.stream().collect(Collectors.groupingBy(MenuDO::getParentMenuId));
            Set<MenuRouteButtonVO> menuRouteButtonVOSet1 = compose2(collect, 0);//第一层
            menuRouteButtonVOSet.addAll(menuRouteButtonVOSet1);
            for (MenuRouteButtonVO menuRouteButtonVO : menuRouteButtonVOSet1) {
                Set<MenuRouteButtonVO> routeButtonVOSet2 = compose2(collect, menuRouteButtonVO.getMenuId());
                menuRouteButtonVO.setMenuRouteButtonVOSet(routeButtonVOSet2);//第二层
                for (MenuRouteButtonVO routeButtonVO : routeButtonVOSet2) {
                    Set<MenuRouteButtonVO> buttonVOSet3 = compose2(collect, routeButtonVO.getMenuId());
                    routeButtonVO.setMenuRouteButtonVOSet(buttonVOSet3);//第三层
                }
            }
        }

        return menuRouteButtonVOSet;
    }

    private Set<MenuRouteButtonVO> compose2(Map<Integer, List<MenuDO>> collect,Integer parentId){
        Set<MenuRouteButtonVO> menuRouteButtonVOSet = new HashSet<>();
        List<MenuDO> menuDOS = collect.get(parentId);
        if(menuDOS==null){
            return menuRouteButtonVOSet;
        }
        for (MenuDO menuDO : menuDOS) {
            MenuRouteButtonVO menuRouteButtonVO = new MenuRouteButtonVO();
            BeanUtils.copyProperties(menuDO,menuRouteButtonVO);
            menuRouteButtonVOSet.add(menuRouteButtonVO);
        }
        return menuRouteButtonVOSet;
    }


}
