import Device

device = Device()

if __name__ == '__main__':
    while True:
        s = input()

        if s == "add":
            minWater, maxWater, waterPumpPort, waterSensorPort = input("minWater maxWater  waterPumpPort  waterSensorPort")
            device.addPot(minWater, maxWater, waterPumpPort, waterSensorPort)

        if s == "close":
            break

        device.runIteration()
