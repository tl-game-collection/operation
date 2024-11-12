 package com.softeem.model.system;

 public class Menu {
     private Integer authorityId; //权限id
     private String authorityName;//权限名称
     private Integer orderNumber;//排序
     private String  menuUrl;//菜单url
     private String  menuIcon; //菜单图标
     private String authority; //权限
     private Integer checked;
     private Integer  isMenu;
     private Integer  parentId;//父节点ID
    public Integer getAuthorityId() {
        return authorityId;
    }
    public String getAuthorityName() {
        return authorityName;
    }
    public Integer getOrderNumber() {
        return orderNumber;
    }
    public String getMenuUrl() {
        return menuUrl;
    }
    public String getMenuIcon() {
        return menuIcon;
    }
    public String getAuthority() {
        return authority;
    }
    public Integer getChecked() {
        return checked;
    }
    public Integer getIsMenu() {
        return isMenu;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }
    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    public void setChecked(Integer checked) {
        this.checked = checked;
    }
    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
     
     
}
