<#include "../parts/sec.ftl">
<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
    <div class="container" style="margin-top: 60px" xmlns="http://www.w3.org/1999/html"
         xmlns="http://www.w3.org/1999/html">
        <div class="row">
            <div class="col-md-6 col-md-offset-4" style="padding-top: 50px">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title" style="display: inline-block"><@spring.message "edit.profile"/></h3>
                    </div>
                    <div class="panel-body">
                        <form action="/admin/save" method="post">
                            <#if successSave??>
                                <div class="alert alert-success"> ${successSave!} </div>
                            </#if>
                            <div class="form-group">
                                <label><@spring.message "username"/>:</label>
                                <tr>
                                    <td>${user.username!}</td>
                                </tr>
                            </div>
                            <div class="form-group">
                                <label><@spring.message "full.name"/>:</label>
                                <tr>
                                    <td>${user.fullName!}</td>
                                </tr>
                            </div>
                            <div class="form-group">
                                <label><@spring.message "role"/></label>

                                <#if roles??>
                                    <div>
                                        <#list roles as role>
                                            <label>
                                                <input type="radio" name="role" ${user.getAuthorities()?seq_contains(role)?string("checked", "")} value="${role}">${role}
                                            </label>
                                        </#list>
                                        <#if user.isMaster()>

                                            <div class="panel-heading">
                                                <h3 class="panel-title" style="display: inline-block"><@spring.message "master.edit"/></h3>
                                            </div>
                                        </#if>
                                    </div>
                                </#if>
                            </div>
                            <input type="hidden" value="${user.id!}" name="userId">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <button type="submit"><@spring.message "save"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>