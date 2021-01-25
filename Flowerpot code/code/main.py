import Device
import RPi.GPIO as gpio

# rotary_clk, rotary_dt, rotary_press
device = Device.Device(15, 16, 18)

# min_water, max_water, pump_pow, pump_in1, pump_in2
device.add_pot(0.4, 0.6, 7, 12, 11)
# device.add_pot(0.4, 0.6, 18, 15, 16)

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
            gpio.cleanup()
            break

        device.run_iteration()
