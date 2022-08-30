package org.springblade.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springblade.modules.system.entity.SysToken;
import org.springblade.modules.system.entity.SysUser;
import org.springblade.modules.system.service.SysUserService;
import org.springblade.modules.user.entity.TechuserToken;
import org.springblade.modules.user.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthRealm extends AuthorizingRealm{
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ShiroService shiroService;

    /**
     * 认证
     * @param token
     * @return
	 */
    @Override
    protected SimpleAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        //获取token，既前端传入的token
        String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = shiroService.findByToken(accessToken);
        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().isBefore(LocalDateTime.now())) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        SysUser user = sysUserService.getById(tokenEntity.getUserCode());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,accessToken,this.getName());
        return simpleAuthenticationInfo;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始授权认证...");
      //  principalCollection.fromRealm(getName())
        return null;
    }
}
