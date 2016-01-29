package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.TeleOp;

@TeleOp(name="VVOpMode2")
public class VVOpMode2 extends SynchronousOpMode{
     DcMotor motorLeft = null;
     DcMotor motorRight = null;
     DcMotor motorTurnLeft = null;
     DcMotor motorTurnRight = null;
     DcMotor Arm = null;
     DcMotor Rotate = null;
     int reverseJoysticks = 1;
     float right = 0;
     float left = 0;

    @Override public void main() throws InterruptedException{
        //Initialize our hardware variables.
        this.motorLeft = this.hardwareMap.dcMotor.get("motorLeft");
        this.motorRight = this.hardwareMap.dcMotor.get("motorRight");
        this.motorTurnLeft = this.hardwareMap.dcMotor.get("motorTurnLeft");
        this.motorTurnRight = this.hardwareMap.dcMotor.get("motorTurnRight");
        this.Arm = this.hardwareMap.dcMotor.get("Arm");
        this.Rotate = this.hardwareMap.dcMotor.get("Rotate");

         //Set motor channel modes
        motorLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorTurnLeft.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorTurnRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorRight.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        //Arm.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        Rotate.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        // Wait for the game to start
        waitForStart();

        // Go go gadget robot!
        while (opModeIsActive()){
            if (updateGamepads()){
                // Controller 1

                    right = reverseJoysticks * (float)scaleInput(gamepad1.right_stick_y);
                    left = reverseJoysticks * (float)scaleInput(gamepad1.left_stick_y);
                    motorLeft.setPower(left);
                    motorRight.setPower(right);
                    if(gamepad1.a)
                        right = right * -1;
                        left = left * -1;
                        motorRight.setPower(right);
                        motorLeft.setPower(left);
                    motorTurnRight.setPower(gamepad1.left_trigger);
                    motorTurnLeft.setPower(gamepad1.left_trigger);
                    motorTurnLeft.setPower(-gamepad1.right_trigger);
                    motorTurnRight.setPower(-gamepad1.right_trigger);
                //Controller 2
                    Rotate.setPower(gamepad2.right_stick_y);
            }
            //telemetry data

            telemetry.update();

            idle();
        }
    }
        //scale the input of a motor to make it easier to control at lower powers.
        public double scaleInput(double dVal){
            double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                    0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

            // get the corresponding index for the scaleInput array.
            int index = (int) (dVal * 16.0);

            // index should be positive.
            if (index < 0)
                index = -index;

            // index cannot exceed size of array minus 1.
            if (index > 16)
                index = 16;

            // get value from the array.
            double dScale = 0.0;
            if (dVal < 0)
                dScale = -scaleArray[index];
            else
                dScale = scaleArray[index];

            return dScale;
        }
}
