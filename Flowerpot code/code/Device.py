import Pot


class Device:

    pots = []

    def add_pot(self, min_water, max_water, water_pump_port, water_sensor_port):
        self.pots.append(Pot.Pot(min_water, max_water, water_pump_port, water_sensor_port))

    def run_iteration(self):
        for pot in self.pots:
            pot.run_iteration()
