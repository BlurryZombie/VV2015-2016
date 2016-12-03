package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Shaffer_904573 on 10/24/2016.
 */
@TeleOp(name="TheOpModeOfDestiny",group="7797")
public class TheOpModeOfDestiny extends LinearOpMode {

    public TheOpModeOfDestiny( ){

    }

    DcMotor leftBackMotor;
    DcMotor rightBackMotor;
    DcMotor leftFrontMotor;
    DcMotor rightFrontMotor;
    DcMotor collection;
    DcMotor cannon;

    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        leftBackMotor  = hardwareMap.dcMotor.get("bl");
        rightBackMotor = hardwareMap.dcMotor.get("br");
        leftFrontMotor  = hardwareMap.dcMotor.get("fl");
        rightFrontMotor = hardwareMap.dcMotor.get("fr");
        collection = hardwareMap.dcMotor.get("cl");
        cannon = hardwareMap.dcMotor.get("cn");

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        collection.setDirection(DcMotor.Direction.FORWARD);
        cannon.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            leftBackMotor.setPower(-gamepad1.left_stick_y);
            rightBackMotor.setPower(-gamepad1.right_stick_y);
            leftFrontMotor.setPower(-gamepad1.left_stick_y);
            rightFrontMotor.setPower(-gamepad1.right_stick_y);
            collection.setPower(-gamepad2.right_stick_y);
            cannon.setPower(-gamepad2.left_stick_y);
        }
    }
}
