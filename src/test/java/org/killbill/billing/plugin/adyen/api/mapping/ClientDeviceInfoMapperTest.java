package org.killbill.billing.plugin.adyen.api.mapping;

import com.google.common.collect.ImmutableMap;
import org.killbill.billing.payment.api.PluginProperty;
import org.killbill.billing.plugin.adyen.api.AdyenPaymentPluginApi;
import org.killbill.billing.plugin.adyen.client.model.PaymentInfo;
import org.killbill.billing.plugin.api.PluginProperties;
import org.testng.annotations.Test;

import static org.killbill.billing.plugin.adyen.api.AdyenPaymentPluginApi.PROPERTY_USER_AGENT;
import static org.testng.Assert.assertEquals;

public class ClientDeviceInfoMapperTest {

    @Test(groups = "fast")
    public void testAndroidClientWithDefaultProperties() {
        Iterable<PluginProperty> deviceInfoProperties = PluginProperties.buildPluginProperties(
                ImmutableMap.<String, String>builder()
                        .put(AdyenPaymentPluginApi.PROPERTY_USER_AGENT, "Mozilla/5.0 (Android 7.0 / Play 17455018; Samsung A5xelte / Samsung SM-A510F; Orange B) app-embedded-web-view")
                        .put(AdyenPaymentPluginApi.PROPERTY_ACCEPT_HEADER, "application/json")
                        .build());
        PaymentInfo paymentInfo = new PaymentInfo();
        String userAgent = getUserAgent(deviceInfoProperties);
        ClientDeviceInfoMapper clientDeviceInfoMapper = ClientDeviceInfoMapper.getClientDeviceInfoMapper(userAgent);
        ClientDeviceInfoMapper.mapDeviceInfo(userAgent, paymentInfo, deviceInfoProperties);

        ClientDeviceInfoMapper expectedClientDeviceInfoMapper = ClientDeviceInfoMapper.ANDROID;

        //Default browser info for Android devices
        Integer expectedScreenHeight = 2960;
        Integer expectedScreenWidth = 1440;
        Integer expectedColorDepth = 24;
        Integer expectedBrowserTimeOffset = 0;
        String expectedBrowserLanguage = "en-GB";
        Boolean expectedJavaEnabled = false;
        Boolean expectedJavascriptEnabled = false;


        assertEquals(clientDeviceInfoMapper, expectedClientDeviceInfoMapper);
        assertEquals(paymentInfo.getScreenHeight(), expectedScreenHeight);
        assertEquals(paymentInfo.getScreenWidth(), expectedScreenWidth);
        assertEquals(paymentInfo.getColorDepth(), expectedColorDepth);
        assertEquals(paymentInfo.getBrowserTimeZoneOffset(), expectedBrowserTimeOffset);
        assertEquals(paymentInfo.getBrowserLanguage(), expectedBrowserLanguage);
        assertEquals(paymentInfo.getJavaEnabled(), expectedJavaEnabled);
        assertEquals(paymentInfo.getJavaScriptEnabled(), expectedJavascriptEnabled);
    }

    @Test(groups = "fast")
    public void testIPhoneClientWithDefaultProperties() {
        Iterable<PluginProperty> deviceInfoProperties = PluginProperties.buildPluginProperties(
                ImmutableMap.<String, String>builder()
                        .put(AdyenPaymentPluginApi.PROPERTY_USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 12_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) app-embedded-web-view")
                        .put(AdyenPaymentPluginApi.PROPERTY_ACCEPT_HEADER, "application/json")
                        .build());
        PaymentInfo paymentInfo = new PaymentInfo();
        String userAgent = getUserAgent(deviceInfoProperties);
        ClientDeviceInfoMapper clientDeviceInfoMapper = ClientDeviceInfoMapper.getClientDeviceInfoMapper(userAgent);
        ClientDeviceInfoMapper.mapDeviceInfo(userAgent, paymentInfo, deviceInfoProperties);

        ClientDeviceInfoMapper expectedClientDeviceInfoMapper = ClientDeviceInfoMapper.IPHONE;

        //Default browser info for IPhone devices
        Integer expectedScreenHeight = 1334;
        Integer expectedScreenWidth = 750;
        Integer expectedColorDepth = 24;
        Integer expectedBrowserTimeOffset = 0;
        String expectedBrowserLanguage = "en-GB";
        Boolean expectedJavaEnabled = false;
        Boolean expectedJavascriptEnabled = false;

        assertEquals(clientDeviceInfoMapper, expectedClientDeviceInfoMapper);
        assertEquals(paymentInfo.getScreenHeight(), expectedScreenHeight);
        assertEquals(paymentInfo.getScreenWidth(), expectedScreenWidth);
        assertEquals(paymentInfo.getColorDepth(), expectedColorDepth);
        assertEquals(paymentInfo.getBrowserTimeZoneOffset(), expectedBrowserTimeOffset);
        assertEquals(paymentInfo.getBrowserLanguage(), expectedBrowserLanguage);
        assertEquals(paymentInfo.getJavaEnabled(), expectedJavaEnabled);
        assertEquals(paymentInfo.getJavaScriptEnabled(), expectedJavascriptEnabled);
    }

    private String getUserAgent(Iterable<PluginProperty> deviceProperties) {
        return PluginProperties.findPluginPropertyValue(PROPERTY_USER_AGENT, deviceProperties);
    }

}
