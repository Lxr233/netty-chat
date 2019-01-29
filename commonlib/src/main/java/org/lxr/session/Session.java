package org.lxr.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: Session对象，表示用户当前的会话信息
 * @create: 2019-01-28 20:26
 **/
@Data
@NoArgsConstructor
public class Session
{
    //用户唯一性标识
    private String userId;
    private String userName;

    public Session(String userId,String userName){
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString(){
        return userId + ":" + userName;
    }
}
