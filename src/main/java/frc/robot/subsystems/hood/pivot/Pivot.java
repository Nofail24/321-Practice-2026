package frc.robot.subsystems.hood.pivot;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.hardware.TalonFX;

public class Pivot {
    TalonFX pivotmotor = new TalonFX(PivotConstants.kPivotMotorID);
public Pivot(){
    final CurrentLimitsConfigs currentLimits = 
    new CurrentLimitsConfigs()
    .withSupplyCurrentLimit(PivotConstants.kSupplyCurrentLimits);
    .withSupplyCurrentLimitEnable(PivotConstants.kSupplyLimitsEnable);
}



}
