package org.killbill.billing.plugin.adyen.api.mapping;

import org.jooq.tools.StringUtils;
import org.killbill.billing.payment.api.PluginProperty;
import org.killbill.billing.plugin.adyen.client.model.PaymentInfo;

import com.google.common.collect.ImmutableSet;
import org.killbill.billing.plugin.api.PluginProperties;

import java.util.Set;

import static org.killbill.billing.plugin.adyen.api.AdyenPaymentPluginApi.PROPERTY_SCREEN_HEIGHT;
import static org.killbill.billing.plugin.adyen.api.AdyenPaymentPluginApi.PROPERTY_SCREEN_WIDTH;

/*
 *  Author: Anchal Jijhotiya
 */
public enum ClientDeviceInfoMapper {

    IPHONE {

        private final Set<String> USER_AGENT_PATTERNS = ImmutableSet.of("IPHONE");

        @Override
        public boolean isDesiredClientDevice(String userAgent) {
            return isDesiredClientDevice(userAgent, USER_AGENT_PATTERNS);
        }

        @Override
        public void setDeviceInfo(final PaymentInfo paymentInfo, final Iterable<PluginProperty> properties) {
            final String screenHeight = PluginProperties.findPluginPropertyValue(PROPERTY_SCREEN_HEIGHT, properties);
            final String screenWidth = PluginProperties.findPluginPropertyValue(PROPERTY_SCREEN_WIDTH, properties);

            if (StringUtils.isBlank(screenHeight)) {
                paymentInfo.setScreenHeight(1334);
            } else {
                paymentInfo.setScreenHeight(Integer.parseInt(screenHeight));
            }

            if (StringUtils.isBlank(screenWidth)) {
                paymentInfo.setScreenWidth(750);
            } else {
                paymentInfo.setScreenWidth(Integer.parseInt(screenWidth));
            }
        }
    },

    ANDROID {

        private final Set<String> USER_AGENT_PATTERNS = ImmutableSet.of("ANDROID");

        @Override
        public boolean isDesiredClientDevice(String userAgent) {
            return isDesiredClientDevice(userAgent, USER_AGENT_PATTERNS);
        }

        @Override
        public void setDeviceInfo(final PaymentInfo paymentInfo, final Iterable<PluginProperty> properties) {
            final String screenHeight = PluginProperties.findPluginPropertyValue(PROPERTY_SCREEN_HEIGHT, properties);
            final String screenWidth = PluginProperties.findPluginPropertyValue(PROPERTY_SCREEN_WIDTH, properties);

            if (StringUtils.isBlank(screenHeight)) {
                paymentInfo.setScreenHeight(2960);
            } else {
                paymentInfo.setScreenHeight(Integer.parseInt(screenHeight));
            }

            if (StringUtils.isBlank(screenWidth)) {
                paymentInfo.setScreenWidth(1440);
            } else {
                paymentInfo.setScreenWidth(Integer.parseInt(screenWidth));
            }
        }
    };

    protected static boolean isDesiredClientDevice(String userAgent, Set<String> devicePatterns) {
        if (StringUtils.isBlank(userAgent)) {
            return false;
        }

        userAgent = userAgent.toUpperCase();
        for (String devicePattern : devicePatterns) {
            if (userAgent.contains(devicePattern)) {
                return true;
            }
        }
        return false;
    }

    /*
        If device is not listed in the ClientDeviceInfoMapper enum. Then setInfoForOtherDevice() should be called to set
        the provided params in the `properties` in `paymentInfo`.
    */
    protected static void setInfoForOtherDevice(final PaymentInfo paymentInfo, final Iterable<PluginProperty> properties) {
        final String screenHeight = PluginProperties.findPluginPropertyValue(PROPERTY_SCREEN_HEIGHT, properties);
        final String screenWidth = PluginProperties.findPluginPropertyValue(PROPERTY_SCREEN_WIDTH, properties);

        if (screenHeight != null) {
            paymentInfo.setScreenHeight(Integer.valueOf(screenHeight));
        }
        if (screenWidth != null) {
            paymentInfo.setScreenWidth(Integer.valueOf(screenWidth));
        }
    }

    public abstract boolean isDesiredClientDevice(String userAgent);
    public abstract void setDeviceInfo(final PaymentInfo paymentInfo, final Iterable<PluginProperty> properties);
}
