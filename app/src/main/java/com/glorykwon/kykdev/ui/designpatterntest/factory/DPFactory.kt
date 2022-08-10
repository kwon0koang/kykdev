package com.glorykwon.kykdev.ui.designpatterntest.factory

import com.glorykwon.kykdev.ui.designpatterntest.devices.DeviceType

/**
 * 팩토리 메소드 패턴, 추상 팩토리 메소드 패턴
 * 구체적으로 어떤 인스턴스륾 만들지는 서브 클래스가 정함
 *      DeviceFactory : 하나의 기기 팩토리에서 타입 별로 기기를 생성
 *      DeviceFactory2 : 각각의 기기를 생산하는 팩토리 생성
 *      AbstractDeviceFactory : 추상 팩토리 메소드 패턴
 */
fun main(){
    // 팩토리 메소드 패턴
    // 하나의 기기 팩토리에서 타입 별로 기기를 생성
    DeviceFactory().createDevice(DeviceType.TV)
    DeviceFactory().createDevice(DeviceType.AIR_CONDITIONER)
    DeviceFactory().createDevice(DeviceType.AIR_CLEANER)

    // 팩토리 메소드 패턴 2
    // 각각의 기기를 생산하는 팩토리 생성
    TvFactory().createDevice()
    AirConditionerFactory().createDevice()
    AirCleanerFactory().createDevice()

    // 추상 팩토리 메소드 패턴
    AbstractDeviceFactory(TvFactory()).createDevice()
    AbstractDeviceFactory(AirConditionerFactory()).createDevice()
    AbstractDeviceFactory(AirCleanerFactory()).createDevice()
}