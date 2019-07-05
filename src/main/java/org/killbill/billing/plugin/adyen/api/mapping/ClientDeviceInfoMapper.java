package org.killbill.billing.plugin.adyen.api.mapping;

import org.jooq.tools.StringUtils;
import org.killbill.billing.payment.api.PluginProperty;
import org.killbill.billing.plugin.adyen.client.model.PaymentInfo;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

/*
 *   Note: We are hardcoding the browser info for below properties and device type.
 *         DeviceTypes          :  [ ANDROID, IPHONE ]
 *         Hard coded properties:  [ screenHeight, screenWidth, colorDepth, browserLanguage, browserTimeZoneOffset,
 *                                 javaEnabled ]
 *
 *         This change will only be present for the first cut of Staging release so that other teams can use the
 *         3DS2.0 flow from the KillBill for Adyen in Staging asap.
 *         This change will be refactored and moved to PSP KillBill plugin to use the values passed by clients
 *         and for backward compatibility of older clients the default values will be populated for the request.
 *         All the browser info properties are need to be extracted at the PSP plugin, which is not the case now.
 *         These extracted properties then only be used to create Adyen request in Adyen plugin which we have
 *         hardcoded for now.
 *
 *         More details for the default values can be seen in this document:
 *         https://docs.google.com/document/d/1qPqqh8XwoPnE3cllpzmEwz1nZfYKIErVso_1TGdbfHE/edit
 *
 */
public enum ClientDeviceInfoMapper {

    IPHONE {

        private final Set<String> USER_AGENT_PATTERNS = ImmutableSet.of("IPHONE");

        @Override
        protected boolean isDesiredClientDevice(String userAgent) {
            return isDesiredClientDevice(userAgent, USER_AGENT_PATTERNS);
        }

        @Override
        protected void setDeviceInfo(final PaymentInfo paymentInfo, final Iterable<PluginProperty> properties) {
            paymentInfo.setScreenHeight(1334);
            paymentInfo.setScreenWidth(750);
            paymentInfo.setColorDepth(24);
            paymentInfo.setBrowserLanguage("en-GB");
            paymentInfo.setBrowserTimeZoneOffset(0);
            paymentInfo.setJavaEnabled(false);
            paymentInfo.setJavaScriptEnabled(false);
        }
    },

    ANDROID {

        private final Set<String> USER_AGENT_PATTERNS = ImmutableSet.of("ANDROID");

        @Override
        protected boolean isDesiredClientDevice(String userAgent) {
            return isDesiredClientDevice(userAgent, USER_AGENT_PATTERNS);
        }

        @Override
        protected void setDeviceInfo(final PaymentInfo paymentInfo, final Iterable<PluginProperty> properties) {
            paymentInfo.setScreenHeight(2960);
            paymentInfo.setScreenWidth(1440);
            paymentInfo.setColorDepth(24);
            paymentInfo.setBrowserLanguage("en-GB");
            paymentInfo.setBrowserTimeZoneOffset(0);
            paymentInfo.setJavaEnabled(false);
            paymentInfo.setJavaScriptEnabled(false);

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

    public static void mapDeviceInfo(final String userAgent, final PaymentInfo paymentInfo, final Iterable<PluginProperty> properties) {
        ClientDeviceInfoMapper clientDeviceInfoMapper = getClientDeviceInfoMapper(userAgent);

        /*
            If device is not listed in the ClientDeviceInfoMapper enum. Then we are calling ClientDeviceInfoMapper.setInfoForOtherDevice()
            to set the provided params in the request.
         */
        if (clientDeviceInfoMapper != null) {
            clientDeviceInfoMapper.setDeviceInfo(paymentInfo, properties);
        }
    }

    public static ClientDeviceInfoMapper getClientDeviceInfoMapper(final String userAgent) {
        ClientDeviceInfoMapper clientDeviceInfoMapper = null;
        for (ClientDeviceInfoMapper clientDeviceMapper : ClientDeviceInfoMapper.values()) {
            if (clientDeviceMapper.isDesiredClientDevice(userAgent)) {
                clientDeviceInfoMapper = clientDeviceMapper;
            }
        }

        return clientDeviceInfoMapper;
    }

    protected abstract boolean isDesiredClientDevice(String userAgent);
    protected abstract void setDeviceInfo(final PaymentInfo paymentInfo, final Iterable<PluginProperty> properties);
}
