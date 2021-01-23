import Device

device = Device.Device()
device.add_pot(0.4, 0.6, 7, 12, 11)

if __name__ == '__main__':
    while True:
        s = input()

        # if s == "add":

        #   min_water = input("min_water")
        #  max_water = input("max_water")

        # pump ports
        #    pump
        #    device.add_pot(minWater, maxWater, waterPumpPort, waterSensorPort)

        if s == "close":
            break

        device.run_iteration()
