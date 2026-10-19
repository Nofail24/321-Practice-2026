package frc.robot.subsystems.pivot;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.hal.FRCNetComm.tInstances;

public class PivotConstants {

    public static final int kPivotMotorID = 42;
    public static final Current kSupplyCurrentLimits = null;
    public static final boolean kSupplyLimitsEnable = true;
    public static final Current kStatorLimit = null;
    public static final boolean kStatorLimitsEnable = false;
    public static final NeutralModeValue kNeutralMode = NeutralModeValue.Brake;
    public static final InvertedValue kInverstion = null;
    public static final double kGearRatio = 142/7;
    public static final Voltage kMaxVoltage = Volts.of(12);
    public static final Current kMaxCurrent = Amps.of(40);
    public static final Angle kStartingAngle = Degrees.of(0);
    public static final String kRobot = "Walter";
    public static final Angle kFeedAngle = Degrees.of(15);
}
