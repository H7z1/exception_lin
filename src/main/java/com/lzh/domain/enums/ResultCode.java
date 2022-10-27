package com.lzh.domain.enums;

import com.lzh.domain.dto.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 返回码实现
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),
    /**
     * 服务异常
     */
    ERROR(-1, "服务异常"),
    /**
     * 业务异常
     */
    FAILURE(400, "业务异常"),
    /**
     * 服务未找到
     */
    NOT_FOUND(404, "服务未找到"),
    /**
     * Too Many Requests
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE(503, "服务不可用"),

    /**
     * 获取当前用户失败
     */
    CURRENT_USER_FAIL(10001, "获取当前用户失败"),
    /**
     * 用户是超级管理员，不可以修改状态
     */
    UPDATE_USER_STATUS(10002, "用户是超级管理员，不可以修改状态"),
    /**
     * 用户是超级管理员，不可以修改密码
     */
    UPDATE_USER_PASSWORD(10003, "用户是超级管理员，不可以修改密码"),
    /**
     * 用户未登录，请登陆后进行访问
     */
    USER_NEED_LOGIN(11001, "用户未登录，请登陆后进行访问"),
    /**
     * 该用户已在其它地方登录
     */
    USER_MAX_LOGIN(11002, "该用户已在其它地方登录"),
    /**
     * 长时间未操作，自动退出
     */
    USER_LOGIN_TIMEOUT(11003, "长时间未操作，自动退出"),
    /**
     * 用户被禁11005用
     */
    USER_DISABLED(11004, "用户被禁11005用"),
    /**
     * 用户被锁定
     */
    USER_LOCKED(11005, "用户被锁定"),
    /**
     * 用户名或密码错误
     */
    USER_PASSWORD_ERROR(11006, "用户名或密码错误"),
    /**
     * 用户密码过期
     */
    USER_PASSWORD_EXPIRED(11007, "用户密码过期"),
    /**
     * 用户账号过期
     */
    USER_ACCOUNT_EXPIRED(11008, "用户账号过期"),
    /**
     * 没有该用户
     */
    USER_NOT_EXIST(11009, "没有该用户"),
    /**
     * 用户登录失败
     */
    USER_LOGIN_FAIL(11010, "用户登录失败"),
    /**
     * 验证码错误
     */
    VERIFY_CODE_ERROR(11011, "验证码错误"),
    /**
     * 用户已存在
     */
    USER_IS_EXIST(11012, "用户已存在"),
    /**
     * 无权访问
     */
    NO_AUTHENTICATION(1003006, "无权访问"),
    /**
     * 角色ID无效
     */
    ROLE_IS_NOT_EXIST(13001, "角色ID无效"),
    /**
     * 角色代码已存在
     */
    ROLE_IS_EXIST(13002, "角色代码已存在"),
    /**
     * 配置信息为空
     */
    CONFIG_ID_IS_NOT_EXIST(14001, "配置信息为空"),
    /**
     * 配置ID无效
     */
    CONFIG_IS_NOT_EXIST(14002, "配置ID无效"),
    /**
     * 配置ID已存在
     */
    CONFIG_IS_EXIST(14002, "配置ID已存在"),
    /**
     * 系统配置不允许修改
     */
    CONFIG_IS_SYSTEM(14003, "系统配置不允许修改"),
    /**
     * 系统配置不允许删除
     */
    CONFIG_IS_NOT_DELETE(14003, "系统配置不允许删除"),
    /**
     * 文件不存在
     */
    FILE_DOES_NOT_EXIST(16001, "文件不存在"),
    /**
     * 文件上传异常
     */
    FILE_UPLOAD_EXCEPTION(16002, "文件上传异常"),
    /**
     * 文件下载异常
     */
    FILE_DOWNLOAD_ABNORMAL(16003, "文件下载异常"),
    /**
     * 无效的资源ID
     */
    RESOURCE_NOT_FIND(12001, "无效的资源ID"),
    /**
     * 资源ID已存在
     */
    RESOURCE_IS_EXIST(12001, "资源ID已存在"),
    /**
     * 无效资源父节点ID
     */
    RESOURCE_PARENT_NOT_FIND(12002, "无效资源父节点ID"),
    /**
     * 无效资源父节点ID
     */
    RESOURCE_PARENT_INVALID(12003, "无效资源父节点ID"),
    /**
     * 该资源下有子资源，不能删除
     */
    RESOURCE_HAVE_SUB(12004, "该资源下有子资源，不能删除"),
    /**
     * 机构已存在
     */
    ORG_IS_EXIST(17001, "机构已存在"),
    /**
     * 机构不存在
     */
    ORG_NOT_EXIST(17002, "机构不存在"),
    /**
     * 机构下存在用户
     */
    ORG_HAVE_USER(17003, "机构下存在用户"),
    /**
     * 无效机构父节点ID
     */
    ORG_PID_ERROR(17004, "无效机构父节点ID"),
    /**
     * 父级节点禁止删除
     */
    ORG_TOP_FORBID(17005, "父级节点禁止删除"),
    /**
     * 机构下存在子机构
     */
    ORG_HAVE_BRANCH(17006, "机构下存在子机构"),
    /**
     * 停用原因不能为空
     */
    ORG_STOP_REASON(17007, "停用原因不能为空"),

    // 字典管理
    /**
     * 父级ID无效
     */
    DICT_PID_ERROR(18001, "父级ID无效"),
    /**
     * ID无效
     */
    DICT_ID_ERROR(18002, "ID无效"),
    /**
     * 字典code已存在
     */
    DICT_CODE_EXIST(18003, "字典code已存在"),
    /**
     * 字典name已存在
     */
    DICT_NAME_EXIST(18004, "字典name已存在"),
    /**
     * 字典下存在数据
     */
    DICT_HAVE_DATA(18005, "字典下存在数据"),
    /**
     * 字典不存在
     */
    DICT_NOT_EXIST(18006, "字典不存在"),
    /**
     * 存在子节点
     */
    DICT_HAVE_SON(18007, "存在子节点"),
    // 数据组
    /**
     * 数据组信息不存在
     */
    GROUP_ID_ERROR(19001, "数据组信息不存在"),
    /**
     * 数据组初始化无机构信息
     */
    GROUP_INIT_DATA_ERROR(19002, "数据组初始化无机构信息"),

    // ======= 系统级异常（10xxxxxx）=======
    //  系统相关异常(1001xxxx)
    DATA_SOURCE_ERROR(10010001, "数据库异常！"),
    //  资源相关异常(1002xxxx)
    //  配置相关异常(1003xxxx)

    // ======= 参数异常(11xxxxxx) =======
    /**
     * 参数错误
     */
    GLOBAL_PARAM_ERROR(11000001, "参数错误"),
    // 参数校验提示
    GLOBAL_VERIFY_ERROR(11000010, "参数验证不通过"),

    // ======= 交互性弹框(1101xxxx) 弹窗上按钮为 "确定" =======
    PAGE_POP_BUTTON_CONFIRM_ONE(11010001,"该项目存在套餐中，需从中移除后方可删除/停用"),


    // ======= 交互性弹框(1102xxxx) 弹窗上按钮为"关闭" =======
    NAME_ALREADY_EXISTS(11020001,"已有相同名称的采购计划，无法重复创建"),

    //  ======= 基础服务相关(1200xxxx) =======
    SMS_CONFIG_ERROR(12000001, "短信服务配置错误"),
    SMS_PLATFORM_RETURN_NULL(12000002, "短信平台未返回数据"),
    SMS_PLATFORM_TEMPLATE_NOT_FOUND(12000003, "短信平台模板未找到"),
    SMS_CONTENT_CONTAIN_FORBIDDEN_WORDS(12000004, "短信内容触发关键词"),
    SMS_PHONE_ERROR(12000005, "短信手机号码无效"),
    SMS_REQUEST_OVER_LIMIT(12000006, "短信请求超过平台限制"),
    SMS_CONTENT_NULL(12000007, "短信模板内容未配置"),
    SMS_CONTENT_OVERLENGTH(12000008, "短信模板内容超长"),

    //  ======= 后台账户管理相关(12xxxxxx) =======
    //  权限相关异常(1201xxxx)

    //  用户相关异常(1202xxxx)
    USER_NOT_FOUND(12020001, "用户信息不存在！"),

    //  角色相关异常(1203xxxx)

    //  菜单相关异常(1204xxxx)

    //  部门相关异常(1205xxxx)

    //  岗位相关异常(1206xxxx)

    //  字典相关异常(1207xxxx)

    //  日志相关异常(1208xxxx)


    // ======= 三方服务厂商(13xxxxxx) =======

    // ======= 内部业务系统(14xxxxxx) =======
    //  预约挂号(1401xxxx)

    //  分诊导医(1402xxxx)

    //  门急诊收费(1403xxxx)

    //  医生工作站(1404xxxx)

    //  护士工作站(1405xxxx)

    //  药房管理(1406xxxx)

    //  医技工作站(1407xxxx)

    //  药库管理(1408xxxx)
    DRUG_REPEAT_CHECK_FAIL_V1(1408001,"药品信息重复（通用名、类型、生产或产地都相同），请检查"),
    DRUG_REPEAT_CHECK_FAIL_V2(1408001,"药品信息重复（通用名、类型、生产厂家、包装规格都相同），请检查"),
    MATERIALS_REPEAT_CHECK_FAIL(1408001,"物资信息重复（物资名称、生产厂家、包装规格都相同），请检查"),
    COMMON_CODE_REPEAT_CHECK_FAIL(1408002,"编码重复！"),
    COMMON_EAN_REPEAT_CHECK_FAIL(1408008,"条形码重复！"),
    COMMON_CODE_ID_NOT_FOUND(1408003,"物品数据不存在！"),
    COMMON_TYPE_NOT_FOUND(1408003,"物品类型数据异常！"),
    COMMON_FIRST_TYPE_NOT_UPDATE(1408004,"物品一级类型不能修改！"),
    COMMON_FIRST_TYPE_NOT_FOUND(1408005,"物品一级类型查找不到！"),
    COMMON_DELETED_FAILURE(1408006,"物品删除失败！"),
    COMMON_DISABLE_TYPE_NOT_NULL(1408007,"可售/禁售状态不能为空！"),

    //  患者主索引(1409xxxx)

    //  运营管理(1410xxxx)

    //  系统管理(1411xxxx)

    //  项目管理(1412xxxx)
    PROJECT_NOT_EXIST(14120000,"项目不存在"),
    COMBO_NOT_EXIST(14120000,"套餐不存在"),
    DATA_NOT_EXIST(14120001,"数据不存在"),
    PROJECT_NAME_NOT_EXIST(14120000,"该项目已存在"),
    COMBO_NAME_NOT_EXIST(14120000,"该套餐已存在"),

    // 锁(1413xxxx)
    LOCK_RETRY(14130000,"请重试"),

    //模板(1414xxxx)
    TEMPLATE_TYPE_ERROR(14140000,"请指定文件夹进行移动"),
    MAX_LEVEL_FOLDER_ERROR(14140001,"目前仅支持新建三级分类"),
    DELETED_FOLDER_EXIST_FILE(14140002,"分类下存在子分类或模板不能删除"),

    //管理端-字典(1415xxxx)
    CMS_DICT_TCM_USES_ERROR(14150000,"相同的症型,治法与方剂不可重复添加")

    //  ======= 外部业务系统(15xxxxxx) =======
    //  LIS(1501xxxx)
    //  PACS(1502xxxx)
    //  合理用药(1503xxxx)
    //  医保(1504xxxx)
    //  三方监管(1505xxxx)
    //  发药机(1506xxxx)
    //  药师审方(1507xxxx)
    //  叫号系统(1508xxxx)
    //  互联网医院(1509xxxx)

    ;


    /**
     * 状态码
     */
    public final int code;
    /**
     * 消息内容
     */
    public final String msg;

    public static ResultCode getResultCode(Integer code) {
        ResultCode[] resultCodeArr = ResultCode.values();
        return Arrays.stream(resultCodeArr).filter(r -> r.getCode() == code).findFirst().orElse(ERROR);
    }

}
