/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.organization.management.governance.connector;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.application.common.model.Property;
import org.wso2.carbon.identity.core.util.IdentityUtil;
import org.wso2.carbon.identity.governance.IdentityMgtConstants;
import org.wso2.carbon.identity.governance.common.IdentityConnectorConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.wso2.carbon.identity.governance.IdentityGovernanceUtil.getPropertyObject;

/**
 * Class which contains Suborganization mgt configs.
 */
public class OrganizationMgtGovernanceConnectorImp implements IdentityConnectorConfig {

    public static final String ONBOARD_ADMIN_TO_SUBORGANIZATION = "Organization.SelfService.OnboardAdminToSubOrg";
    public static final String ENABLE_ADMIN_EMAIL_VERIFICATION = "Organization.SelfService.AdminEmailVerification";
    public static final String ENABLE_SELF_SERVICE = "Organization.SelfService.Enable";

    public static final String ENABLE_SELF_SERVICE_AUTO_LOGIN = "Organization.SelfService.EnableAutoLogin";

    private static final String CONNECTOR_NAME = "organization-self-service";
    private static final String CATEGORY = "User Onboarding";
    private static final String FRIENDLY_NAME = "Suborganization Self Service";

    @Override
    public String getName() {

        return CONNECTOR_NAME;
    }

    @Override
    public String getFriendlyName() {

        return FRIENDLY_NAME;
    }

    @Override
    public String getCategory() {

        return CATEGORY;
    }

    @Override
    public String getSubCategory() {

        return "DEFAULT";
    }

    @Override
    public int getOrder() {

        return 0;
    }

    @Override
    public Map<String, String> getPropertyNameMapping() {

        Map<String, String> nameMapping = new HashMap<>();
        nameMapping.put(ENABLE_SELF_SERVICE, "Enable suborganization self service");
        nameMapping.put(ENABLE_ADMIN_EMAIL_VERIFICATION, "Enable admin email verification");
        nameMapping.put(ONBOARD_ADMIN_TO_SUBORGANIZATION, "Onboard admin to root organization");
        nameMapping.put(ENABLE_SELF_SERVICE_AUTO_LOGIN, "Auto login after self service");
        return nameMapping;
    }

    @Override
    public Map<String, String> getPropertyDescriptionMapping() {

        Map<String, String> descriptionMapping = new HashMap<>();
        descriptionMapping.put(ENABLE_SELF_SERVICE,
                "Allow user's to self service suborganization to the system.");
        descriptionMapping.put(ENABLE_ADMIN_EMAIL_VERIFICATION,
                "User gets email verification before proceeding with suborganization creation.");
        descriptionMapping.put(ONBOARD_ADMIN_TO_SUBORGANIZATION,
                "User gets onboard as admin in suborganization");
        descriptionMapping.put(ENABLE_SELF_SERVICE_AUTO_LOGIN,
                "After self service admin auto login to organization");
        return descriptionMapping;
    }

    @Override
    public String[] getPropertyNames() {

        List<String> properties = new ArrayList<>();
        properties.add(ENABLE_SELF_SERVICE);
        properties.add(ENABLE_ADMIN_EMAIL_VERIFICATION);
        properties.add(ONBOARD_ADMIN_TO_SUBORGANIZATION);
        properties.add(ENABLE_SELF_SERVICE_AUTO_LOGIN);
        return properties.toArray(new String[0]);
    }

    @Override
    public Properties getDefaultPropertyValues(String tenantDomain) {

        String enableSelfService = "false";
        String enableAdminEmailVerification = "false";
        String onboardAdminToSubOrganization = "false";
        String autoLoginAfterAdminOnboarding = "false";

        Map<String, String> defaultProperties = new HashMap<>();
        defaultProperties.put(ENABLE_SELF_SERVICE, getIdentityProperty(ENABLE_SELF_SERVICE, enableSelfService));
        defaultProperties.put(ENABLE_ADMIN_EMAIL_VERIFICATION,
                getIdentityProperty(ENABLE_ADMIN_EMAIL_VERIFICATION, enableAdminEmailVerification));
        defaultProperties.put(ONBOARD_ADMIN_TO_SUBORGANIZATION,
                getIdentityProperty(ONBOARD_ADMIN_TO_SUBORGANIZATION, onboardAdminToSubOrganization));
        defaultProperties.put(ENABLE_SELF_SERVICE_AUTO_LOGIN,
                getIdentityProperty(ENABLE_SELF_SERVICE_AUTO_LOGIN, autoLoginAfterAdminOnboarding));

        Properties properties = new Properties();
        properties.putAll(defaultProperties);
        return properties;
    }

    @Override
    public Map<String, String> getDefaultPropertyValues(String[] propertyNames, String tenantDomain) {

        return null;
    }

    @Override
    public Map<String, Property> getMetaData() {

        Map<String, Property> meta = new HashMap<>();
        meta.put(ENABLE_SELF_SERVICE, getPropertyObject(IdentityMgtConstants.DataTypes.BOOLEAN.getValue()));
        meta.put(ENABLE_ADMIN_EMAIL_VERIFICATION, getPropertyObject(IdentityMgtConstants.DataTypes.BOOLEAN.getValue()));
        meta.put(ENABLE_SELF_SERVICE_AUTO_LOGIN,
                getPropertyObject(IdentityMgtConstants.DataTypes.BOOLEAN.getValue()));
        meta.put(ONBOARD_ADMIN_TO_SUBORGANIZATION,
                getPropertyObject(IdentityMgtConstants.DataTypes.BOOLEAN.getValue()));
        return meta;
    }

    private String getIdentityProperty(String property, String defaultValue) {

        String value = IdentityUtil.getProperty(property);
        if (StringUtils.isNotEmpty(value)) {
            return value;
        }
        return defaultValue;
    }
}
