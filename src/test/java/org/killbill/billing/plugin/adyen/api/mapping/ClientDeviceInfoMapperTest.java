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
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_LANGUAGE, "de")
                        .put(AdyenPaymentPluginApi.PROPERTY_COLOR_DEPTH, "32")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_TIME_ZONE_OFFSET, "-420")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_ENABLED, "false")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_SCRIPT_ENABLED, "true")
                        .build());
        PaymentInfo paymentInfo = new PaymentInfo();
        String userAgent = getUserAgent(deviceInfoProperties);
        ClientDeviceInfoMapper clientDeviceInfoMapper = ClientDeviceInfoMapper.getClientDeviceInfoMapper(userAgent);
        ClientDeviceInfoMapper.mapDeviceInfo(userAgent, paymentInfo, deviceInfoProperties);

        ClientDeviceInfoMapper expectedClientDeviceInfoMapper = ClientDeviceInfoMapper.ANDROID;

        //Default screen resolution for Android devices
        Integer expectedScreenHeight = 2960;
        Integer expectedScreenWidth = 1440;

        assertEquals(clientDeviceInfoMapper, expectedClientDeviceInfoMapper);
        assertEquals(paymentInfo.getScreenHeight(), expectedScreenHeight);
        assertEquals(paymentInfo.getScreenWidth(), expectedScreenWidth);
    }

    @Test(groups = "fast")
    public void testAndroidClientWithoutDefaultProperties() {
        Iterable<PluginProperty> deviceInfoProperties = PluginProperties.buildPluginProperties(
                ImmutableMap.<String, String>builder()
                        .put(AdyenPaymentPluginApi.PROPERTY_USER_AGENT, "Mozilla/5.0 (Android 7.0 / Play 17455018; Samsung A5xelte / Samsung SM-A510F; Orange B) app-embedded-web-view")
                        .put(AdyenPaymentPluginApi.PROPERTY_ACCEPT_HEADER, "application/json")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_LANGUAGE, "de")
                        .put(AdyenPaymentPluginApi.PROPERTY_COLOR_DEPTH, "32")
                        .put(AdyenPaymentPluginApi.PROPERTY_SCREEN_HEIGHT, "1125")
                        .put(AdyenPaymentPluginApi.PROPERTY_SCREEN_WIDTH, "2436")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_TIME_ZONE_OFFSET, "-420")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_ENABLED, "false")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_SCRIPT_ENABLED, "true")
                        .build());

        PaymentInfo paymentInfo = new PaymentInfo();
        String userAgent = getUserAgent(deviceInfoProperties);
        ClientDeviceInfoMapper clientDeviceInfoMapper = ClientDeviceInfoMapper.getClientDeviceInfoMapper(userAgent);
        ClientDeviceInfoMapper.mapDeviceInfo(userAgent, paymentInfo, deviceInfoProperties);

        ClientDeviceInfoMapper expectedClientDeviceInfoMapper = ClientDeviceInfoMapper.ANDROID;

        Integer expectedScreenHeight = Integer.parseInt(
                PluginProperties.findPluginPropertyValue(AdyenPaymentPluginApi.PROPERTY_SCREEN_HEIGHT, deviceInfoProperties)
        );
        Integer expectedScreenWidth = Integer.parseInt(
                PluginProperties.findPluginPropertyValue(AdyenPaymentPluginApi.PROPERTY_SCREEN_WIDTH, deviceInfoProperties)
        );

        assertEquals(clientDeviceInfoMapper, expectedClientDeviceInfoMapper);
        assertEquals(paymentInfo.getScreenHeight(), expectedScreenHeight);
        assertEquals(paymentInfo.getScreenWidth(), expectedScreenWidth);
    }

    @Test(groups = "fast")
    public void testIPhoneClientWithDefaultProperties() {
        Iterable<PluginProperty> deviceInfoProperties = PluginProperties.buildPluginProperties(
                ImmutableMap.<String, String>builder()
                        .put(AdyenPaymentPluginApi.PROPERTY_USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 12_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) app-embedded-web-view")
                        .put(AdyenPaymentPluginApi.PROPERTY_ACCEPT_HEADER, "application/json")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_LANGUAGE, "de")
                        .put(AdyenPaymentPluginApi.PROPERTY_COLOR_DEPTH, "32")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_TIME_ZONE_OFFSET, "-420")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_ENABLED, "false")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_SCRIPT_ENABLED, "true")
                        .build());
        PaymentInfo paymentInfo = new PaymentInfo();
        String userAgent = getUserAgent(deviceInfoProperties);
        ClientDeviceInfoMapper clientDeviceInfoMapper = ClientDeviceInfoMapper.getClientDeviceInfoMapper(userAgent);
        ClientDeviceInfoMapper.mapDeviceInfo(userAgent, paymentInfo, deviceInfoProperties);

        ClientDeviceInfoMapper expectedClientDeviceInfoMapper = ClientDeviceInfoMapper.IPHONE;

        //Default screen resolution for Android devices
        Integer expectedScreenHeight = 1334;
        Integer expectedScreenWidth = 750;

        assertEquals(clientDeviceInfoMapper, expectedClientDeviceInfoMapper);
        assertEquals(paymentInfo.getScreenHeight(), expectedScreenHeight);
        assertEquals(paymentInfo.getScreenWidth(), expectedScreenWidth);
    }

    @Test(groups = "fast")
    public void testIPhoneClientWithoutDefaultProperties() {
        Iterable<PluginProperty> deviceInfoProperties = PluginProperties.buildPluginProperties(
                ImmutableMap.<String, String>builder()
                        .put(AdyenPaymentPluginApi.PROPERTY_USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 12_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) app-embedded-web-view")
                        .put(AdyenPaymentPluginApi.PROPERTY_ACCEPT_HEADER, "application/json")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_LANGUAGE, "de")
                        .put(AdyenPaymentPluginApi.PROPERTY_COLOR_DEPTH, "32")
                        .put(AdyenPaymentPluginApi.PROPERTY_SCREEN_HEIGHT, "1125")
                        .put(AdyenPaymentPluginApi.PROPERTY_SCREEN_WIDTH, "2436")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_TIME_ZONE_OFFSET, "-420")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_ENABLED, "false")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_SCRIPT_ENABLED, "true")
                        .build());

        PaymentInfo paymentInfo = new PaymentInfo();
        String userAgent = getUserAgent(deviceInfoProperties);
        ClientDeviceInfoMapper clientDeviceInfoMapper = ClientDeviceInfoMapper.getClientDeviceInfoMapper(userAgent);
        ClientDeviceInfoMapper.mapDeviceInfo(userAgent, paymentInfo, deviceInfoProperties);

        ClientDeviceInfoMapper expectedClientDeviceInfoMapper = ClientDeviceInfoMapper.IPHONE;

        Integer expectedScreenHeight = Integer.parseInt(
                PluginProperties.findPluginPropertyValue(AdyenPaymentPluginApi.PROPERTY_SCREEN_HEIGHT, deviceInfoProperties)
        );
        Integer expectedScreenWidth = Integer.parseInt(
                PluginProperties.findPluginPropertyValue(AdyenPaymentPluginApi.PROPERTY_SCREEN_WIDTH, deviceInfoProperties)
        );

        assertEquals(clientDeviceInfoMapper, expectedClientDeviceInfoMapper);
        assertEquals(paymentInfo.getScreenHeight(), expectedScreenHeight);
        assertEquals(paymentInfo.getScreenWidth(), expectedScreenWidth);
    }

    @Test(groups = "fast")
    public void testOtherClientsWithoutDefaultProperties() {
        Iterable<PluginProperty> deviceInfoProperties = PluginProperties.buildPluginProperties(
                ImmutableMap.<String, String>builder()
                        .put(AdyenPaymentPluginApi.PROPERTY_USER_AGENT, "Mozilla/5.0 (Windows; CPU Web OS 12_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) app-embedded-web-view")
                        .put(AdyenPaymentPluginApi.PROPERTY_ACCEPT_HEADER, "application/json")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_LANGUAGE, "de")
                        .put(AdyenPaymentPluginApi.PROPERTY_COLOR_DEPTH, "32")
                        .put(AdyenPaymentPluginApi.PROPERTY_SCREEN_HEIGHT, "1125")
                        .put(AdyenPaymentPluginApi.PROPERTY_SCREEN_WIDTH, "2436")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_TIME_ZONE_OFFSET, "-420")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_ENABLED, "false")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_SCRIPT_ENABLED, "true")
                        .build());

        PaymentInfo paymentInfo = new PaymentInfo();
        String userAgent = getUserAgent(deviceInfoProperties);
        ClientDeviceInfoMapper clientDeviceInfoMapper = ClientDeviceInfoMapper.getClientDeviceInfoMapper(userAgent);
        ClientDeviceInfoMapper.mapDeviceInfo(userAgent, paymentInfo, deviceInfoProperties);

        ClientDeviceInfoMapper expectedClientDeviceInfoMapper = null;

        Integer expectedScreenHeight = Integer.parseInt(
                PluginProperties.findPluginPropertyValue(AdyenPaymentPluginApi.PROPERTY_SCREEN_HEIGHT, deviceInfoProperties)
        );
        Integer expectedScreenWidth = Integer.parseInt(
                PluginProperties.findPluginPropertyValue(AdyenPaymentPluginApi.PROPERTY_SCREEN_WIDTH, deviceInfoProperties)
        );

        assertEquals(clientDeviceInfoMapper, expectedClientDeviceInfoMapper);
        assertEquals(paymentInfo.getScreenHeight(), expectedScreenHeight);
        assertEquals(paymentInfo.getScreenWidth(), expectedScreenWidth);
    }

    @Test(groups = "fast")
    public void testOtherClientsWithoutProperties() {
        Iterable<PluginProperty> deviceInfoProperties = PluginProperties.buildPluginProperties(
                ImmutableMap.<String, String>builder()
                        .put(AdyenPaymentPluginApi.PROPERTY_USER_AGENT, "Mozilla/5.0 (Windows; CPU Web OS 12_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) app-embedded-web-view")
                        .put(AdyenPaymentPluginApi.PROPERTY_ACCEPT_HEADER, "application/json")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_LANGUAGE, "de")
                        .put(AdyenPaymentPluginApi.PROPERTY_COLOR_DEPTH, "32")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_TIME_ZONE_OFFSET, "-420")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_ENABLED, "false")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_SCRIPT_ENABLED, "true")
                        .build());

        PaymentInfo paymentInfo = new PaymentInfo();
        String userAgent = getUserAgent(deviceInfoProperties);
        ClientDeviceInfoMapper clientDeviceInfoMapper = ClientDeviceInfoMapper.getClientDeviceInfoMapper(userAgent);
        ClientDeviceInfoMapper.mapDeviceInfo(userAgent, paymentInfo, deviceInfoProperties);

        ClientDeviceInfoMapper expectedClientDeviceInfoMapper = null;

        assertEquals(clientDeviceInfoMapper, expectedClientDeviceInfoMapper);
        assertEquals(paymentInfo.getScreenHeight(), null);
        assertEquals(paymentInfo.getScreenWidth(), null);
    }

    @Test(groups = "fast")
    public void testWithoutUserAgent() {
        Iterable<PluginProperty> deviceInfoProperties = PluginProperties.buildPluginProperties(
                ImmutableMap.<String, String>builder()
                        .put(AdyenPaymentPluginApi.PROPERTY_ACCEPT_HEADER, "application/json")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_LANGUAGE, "de")
                        .put(AdyenPaymentPluginApi.PROPERTY_COLOR_DEPTH, "32")
                        .put(AdyenPaymentPluginApi.PROPERTY_SCREEN_HEIGHT, "1125")
                        .put(AdyenPaymentPluginApi.PROPERTY_SCREEN_WIDTH, "2436")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_TIME_ZONE_OFFSET, "-420")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_ENABLED, "false")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_SCRIPT_ENABLED, "true")
                        .build());

        PaymentInfo paymentInfo = new PaymentInfo();
        String userAgent = getUserAgent(deviceInfoProperties);
        ClientDeviceInfoMapper clientDeviceInfoMapper = ClientDeviceInfoMapper.getClientDeviceInfoMapper(userAgent);
        ClientDeviceInfoMapper.mapDeviceInfo(userAgent, paymentInfo, deviceInfoProperties);

        ClientDeviceInfoMapper expectedClientDeviceInfoMapper = null;

        Integer expectedScreenHeight = Integer.parseInt(
                PluginProperties.findPluginPropertyValue(AdyenPaymentPluginApi.PROPERTY_SCREEN_HEIGHT, deviceInfoProperties)
        );
        Integer expectedScreenWidth = Integer.parseInt(
                PluginProperties.findPluginPropertyValue(AdyenPaymentPluginApi.PROPERTY_SCREEN_WIDTH, deviceInfoProperties)
        );

        assertEquals(clientDeviceInfoMapper, expectedClientDeviceInfoMapper);
        assertEquals(paymentInfo.getScreenHeight(), expectedScreenHeight);
        assertEquals(paymentInfo.getScreenWidth(), expectedScreenWidth);
    }

    @Test(groups = "fast")
    public void testWithoutUserAgentAndScreenProperties() {
        Iterable<PluginProperty> deviceInfoProperties = PluginProperties.buildPluginProperties(
                ImmutableMap.<String, String>builder()
                        .put(AdyenPaymentPluginApi.PROPERTY_ACCEPT_HEADER, "application/json")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_LANGUAGE, "de")
                        .put(AdyenPaymentPluginApi.PROPERTY_COLOR_DEPTH, "32")
                        .put(AdyenPaymentPluginApi.PROPERTY_BROWSER_TIME_ZONE_OFFSET, "-420")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_ENABLED, "false")
                        .put(AdyenPaymentPluginApi.PROPERTY_JAVA_SCRIPT_ENABLED, "true")
                        .build());

        PaymentInfo paymentInfo = new PaymentInfo();
        String userAgent = getUserAgent(deviceInfoProperties);
        ClientDeviceInfoMapper clientDeviceInfoMapper = ClientDeviceInfoMapper.getClientDeviceInfoMapper(userAgent);
        ClientDeviceInfoMapper.mapDeviceInfo(userAgent, paymentInfo, deviceInfoProperties);

        ClientDeviceInfoMapper expectedClientDeviceInfoMapper = null;

        assertEquals(clientDeviceInfoMapper, expectedClientDeviceInfoMapper);
        assertEquals(paymentInfo.getScreenHeight(), null);
        assertEquals(paymentInfo.getScreenWidth(), null);
    }

    private String getUserAgent(Iterable<PluginProperty> deviceProperties) {
        return PluginProperties.findPluginPropertyValue(PROPERTY_USER_AGENT, deviceProperties);
    }

}
