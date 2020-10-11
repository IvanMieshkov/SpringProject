<#include "../parts/sec.ftl">
<#import "../parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<@c.page>
    <div class="container" style="margin-top: 60px">
        <div class="row">
            <div class="col-md-6 col-md-offset-3" style="padding-top: 50px">
                <h2>
                    <@spring.message "user.list"/> <br/>
                </h2>
                <table class="table table-bordered  table-hover table-sm" style=" margin: auto;">
                    <tr>
                        <th><@spring.message "email"/></th>
                        <th><@spring.message "username"/></th>
                        <th><@spring.message "full.name"/></th>
                        <th><@spring.message "role"/></th>
                        <th><@spring.message "edit.profile"/></th>
                    </tr>
                    <#if users??>
                        <#list users as i>
                            <tr>
                                <td>${i.email!}</td>
                                <td>${i.username!}</td>
                                <td>${i.fullName!}</td>
                                <td>${i.role!}</td>
                                <td><a href="/admin/edituser/${i.id!}"> <@spring.message "edit.profile"/></a></td>
                            </tr>
                        </#list>
                    </#if>
                </table>
            </div>
        </div>
    </div>
</@c.page>
