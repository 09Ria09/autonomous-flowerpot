import Device

device = Device.Device()

if __name__ == '__main__':
    while True:
        s = input()

        if s == "add":
            minWater, maxWater, waterPumpPort, waterSensorPort = input("minWater maxWater  waterPumpPort  waterSensorPort")
            device.add_pot(minWater, maxWater, waterPumpPort, waterSensorPort)

        if s == "close":
            break

        device.run_iteration()
