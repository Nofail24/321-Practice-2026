package frc.robot.subsystems.hood;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;

public class HoodConstants {
    public static final Voltage kMaxVoltage = Volts.of(12);
    public static final Current kMaxCurrent = Amps.of(40);
    public static final Angle kStartingAngle = Degrees.of(0);
    public static final int kHoodMotorID = 11;
    public static final double kGearRatio = 142.9;
    public static final InvertedValue kInverstion = InvertedValue.CounterClockwise_Positive;
    public static final NeutralModeValue kMode = NeutralModeValue.Brake;
    public static final Current kSupplyCurrentLimit = Amps.of(40);
    public static final boolean kSupplyLimitEnabled = true;
    public static final Current kstatorLimit = Amps.of(40);
    public static final boolean kstatorLimitEnable = true;
    public static final String kRobot = "Walter";
    public static final Angle kFeedAngle = Degrees.of(15);
    public static final double kFedAngle = kFeedAngle.in(Degrees);
    public static final double kFeeedAngle = kFeedAngle.in(Radians);
    public static final NeutralModeValue kNeutralMode = NeutralModeValue.Brake;
    public static final double kSensor = 142/7 ;
}
