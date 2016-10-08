package com.zkr.peoplehomedoc.utils;

import android.content.Context;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @PackageName=---------cn.com.sinosoft.wjwapp.utils
 * @Description=---------角色常量类
 * @author=---------------LF
 * @date=-----------------2016/9/8 14:47
 * @Copyright=-----------中科软
 */
public class RolesConstant {

    private OptsharepreInterface sharePre;
    private List<Map<String,Object>> roles;

    public RolesConstant(Context context){
        sharePre=new OptsharepreInterface(context);
    }

    /**
     * 根据角色模块获取权限
     * @param arrRoles  访问的模块的权限数组
     * @return
     */
    public boolean getPermissionByUser(String[] arrRoles){
        boolean bl=false;
        List list;
        String roleId;
        try {
            roles= JsonUtils.getListMap(sharePre.getPres("roles").toString());
            for(int i=0;i<roles.size();i++){
                list= Arrays.asList(arrRoles);
                roleId=roles.get(i).get("roleId").toString();
                if(list.contains(roleId)){
                    bl=true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bl;
    }

    /**
     * 标准管理
     */
    //标准管理-管理机构
    public static String SMGRMANAGER ="ROLE000128121010";
    //标准管理-起草单位
    public static String SMGRDRAFT ="ROLE000138121116";
    //标准管理-专家
    public static String SMGREXPERT ="ROLE100134121010";
    //标准管理-委员
    public static String SMGRCOMMITTEE = "ROLE000139121123";

    /**
     * 标准管理列表所需权限,管理列表详情页面
     * value={SMGRMANAGER,SMGRCOMMITTEE,SMGRDRAFT,SMGREXPERT}
     */
    public static String[] PROJECT_LIST={RolesConstant.SMGRMANAGER,RolesConstant.SMGRDRAFT
            ,RolesConstant.SMGREXPERT,RolesConstant.SMGRCOMMITTEE};
    /**
     * 标准管理-专家投票
     *value={SMGREXPERT,SMGRCOMMITTEE}
     */
    public static String[] PROJECT_VOTE={RolesConstant.SMGREXPERT,
            RolesConstant.SMGRCOMMITTEE
            };
    /**
     * 标准管理-专家投票
     *value={SMGRMANAGER}
     */
    public static String[] PROJECT_VOTE_RESULT={RolesConstant.SMGRMANAGER
    };
    /**
     * 标准管理-专家详情
     *value={SMGRMANAGER}
     */
    public static String[] PROJECT_EXPERT={RolesConstant.SMGREXPERT
    };



    /**
     * 测评管理
     */
    //标准测评-国家级管理机构(部级审批员)
    public static String STESTMANAGER ="ROLE100143130528";
    //标准测评-省级审批员
    public static String STESTAPPROVALPROVINCE ="ROLE100139121123";
    //标准测评-省级医疗管理机构
    public static String STESTMANAGERPROVINCEYL ="ROLE100139121123";
    //标准测评-省级卫生管理机构
    public static String STESTMANAGERPROVINCEWS ="ROLE000151140731";
    //标准测评-省级监管机构
    public static String STESTMANAGERPROVINCEJG ="ROLE100139121023";

    /**
     * 检测机构授权所需权限,重置专家密码，专家信息审核
     * value={STESTMANAGER,STESTMANAGERPROVINCEJG,STESTMANAGERPROVINCEWS,STESTMANAGERPROVINCEYL}
     */
    public static String[] INAGENCY_LIST={RolesConstant.STESTMANAGER,RolesConstant.STESTMANAGERPROVINCEJG
            ,RolesConstant.STESTMANAGERPROVINCEWS,RolesConstant.STESTMANAGERPROVINCEYL};

    /**
     * 专家信息审核所需权限
     * value={STESTMANAGER,STESTMANAGERPROVINCEJG,STESTMANAGERPROVINCEWS,STESTMANAGERPROVINCEYL}
     */
    public static String[] EXPERT_AUDIT_LIST={RolesConstant.STESTMANAGER,RolesConstant.STESTMANAGERPROVINCEJG
            ,RolesConstant.STESTMANAGERPROVINCEWS,RolesConstant.STESTMANAGERPROVINCEYL};

    /**
     * 项目评价所需权限
     * value=STESTMANAGER
     */
    public static String[] PROJECT_EVALUATION_LIST={RolesConstant.STESTMANAGER};

    /**
     * 申请机构审核所需权限
     * value={STESTMANAGER,STESTMANAGERPROVINCEJG,STESTMANAGERPROVINCEWS,STESTMANAGERPROVINCEYL}
     */
    public static String[] ORG_AUDIT_LIST={RolesConstant.STESTMANAGER,RolesConstant.STESTMANAGERPROVINCEJG
            ,RolesConstant.STESTMANAGERPROVINCEWS,RolesConstant.STESTMANAGERPROVINCEYL};


}
