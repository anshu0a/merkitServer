package com.merkit.mail;

public class Mail {
	public final static String OTP = """
									<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>Merkit Verification</title>
			</head>

			<body style="margin:0; padding:0; background:#ffffff; font-family:Arial,Helvetica,sans-serif; color:#272b31;">
			<div style="width:100%%; padding:45px 20px; background:#ffffff;">
			<div style="width:360px; max-width:100%%; margin:0 auto;">
			<div style="width:44px; height:44px; margin:0 auto 22px; border-radius:50%%; background:#f4f4f5; border:1px solid #e3e3e5; text-align:center; line-height:44px; font-size:19px;">
			🔐
			</div>
			<div style="text-align:center; font-size:21px; font-weight:700; letter-spacing:-0.3px; color:#292d33;">
			Verify your account
			</div>
			<div style="margin-top:8px; text-align:center; font-size:11px; color:#777c83;">
			One last step to continue with Merkit.
			</div>
			<div style="margin-top:32px; font-size:12px; line-height:20px; color:#555b63;">
			Hello <strong style="color:#292d33;">
				%s
			</strong>,
			<br><br>
			We received a request to verify your Merkit account. Enter the verification code below to complete the process.
			</div>

			<div style="margin-top:27px; text-align:center;">

			<div style="font-size:9px; font-weight:600; color:#777c83; text-transform:uppercase; letter-spacing:1.5px; margin-bottom:10px;">
			Verification code
			</div>

			<div style="display:inline-block; min-width:170px; padding:14px 20px; background:#f5f5f6; border:1px solid #dedfe2; border-radius:7px; color:#292d33; font-size:27px; font-weight:700; letter-spacing:7px;">
				%s
			</div>

			</div>

			<div style="margin-top:24px; padding:13px 15px; border-left:3px solid #cfd1d5; background:#fafafa;">
			<div style="font-size:10px; color:#777c83; line-height:18px;">
			This verification code expires at <strong style="color:#3b4046;">
				%s
			</strong>.
			</div>
			</div>

			<div style="margin-top:28px; padding-top:20px; border-top:1px solid #eeeeef;">

			<div style="font-size:10px; line-height:18px; color:#7a7f86;">
			<strong style="color:#4d5259;">Keep your account secure</strong>
			<br>
			Never share this code with anyone. Merkit will never ask you for your verification code.
			</div>

			</div>

			<div style="margin-top:18px; font-size:10px; line-height:18px; color:#858a91;">
			If you did not request this verification, you can safely ignore this email.
			</div>
			<div style="margin-top:32px; padding-top:18px; border-top:1px solid #eeeeef; text-align:center; font-size:9px; line-height:16px; color:#a1a5aa;">
			This is an automated message. Please do not reply.
			<br>
			© 2026 Merkit · All rights reserved.
			</div>
			</div>
			</div>
			</body>
			</html>	""";

	public static final String FORGOT = """
			<html>
			<!DOCTYPE html>
			<html lang="en">

			<head>
			    <meta charset="UTF-8">
			    <meta name="viewport" content="width=device-width, initial-scale=1.0">
			    <title>Reset Your Password</title>

			    <style>
			        * {
			            box-sizing: border-box;
			        }

			        body {
			            margin: 0;
			            padding: 0;
			            background: #f4f5f7;
			            font-family: Arial, Helvetica, sans-serif;
			            color: #252a31;
			        }

			        .container {
			            width: 100%%;
			            max-width: 600px;
			            margin: 45px auto;
			            padding: 0 18px;
			        }

			        .card {
			            background: #ffffff;
			            border: 1px solid #e4e6e9;
			            border-radius: 12px;
			            padding: 35px 30px;
			            box-shadow: 0 4px 18px rgba(0, 0, 0, 0.05);
			        }
			        
			        h1 {
			            margin: 0;
			            font-size: 22px;
			            font-weight: 700;
			            text-align: center;
			            color: #20242a;
			        }

			        .subtitle {
			            margin: 9px 0 28px;
			            text-align: center;
			            font-size: 12px;
			            line-height: 18px;
			            color: #777d85;
			        }

			        .message {
			            font-size: 12px;
			            line-height: 20px;
			            color: #555b63;
			            margin-bottom: 22px;
			        }

			        .message strong {
			            color: #252a31;
			        }

			        .info {
			            background: #f8f9fa;
			            border: 1px solid #e4e6e9;
			            border-radius: 8px;
			            padding: 14px 16px;
			            margin-bottom: 25px;
			        }

			        .info-row {
			            font-size: 11px;
			            line-height: 20px;
			            color: #6d737b;
			        }

			        .info-row strong {
			            color: #343a40;
			        }

			        .reset-btn {
			            display: block;
			            width: 100%%;
			            padding: 13px 20px;
			            border-radius: 7px;
			            background: #343a40;
			            color: #ffffff !important;
			            font-size: 12px;
			            font-weight: 600;
			            text-align: center;
			            text-decoration: none;
			        }

			        .reset-btn:hover {
			            background: #252a2f;
			        }

			        .security {
			            margin-top: 25px;
			            padding-top: 20px;
			            border-top: 1px solid #eeeeee;
			            font-size: 10px;
			            line-height: 17px;
			            color: #777d85;
			        }

			        .warning {
			            margin-top: 14px;
			            padding: 10px 12px;
			            background: #fafafa;
			            border-radius: 6px;
			            font-size: 10px;
			            line-height: 16px;
			            color: #777d85;
			        }

			        .footer {
			            margin-top: 20px;
			            text-align: center;
			            font-size: 9px;
			            line-height: 16px;
			            color: #9ca1a7;
			        }
			    </style>
			</head>

			<body>

			    <div class="container">

			        <div class="card">
			            <h1>
			                Reset Your Password
			            </h1>

			            <p class="subtitle">
			                We received a request to reset your Merkit account password.
			            </p>

			            <div class="message">
			                Hello <a hreg="#"><strong>%s</strong></a>,
			                <br><br>
			                Someone requested a password reset for your account.
			                Click the button below to create a new password.
			            </div>

			            <div class="info">

			                <div class="info-row">
			                    <strong>Account:</strong> %s
			                </div>

			                <div class="info-row">
			                    <strong>Link expires:</strong> %s
			                </div>
			                <div class="info-row">
			                    <strong>Link:</strong> %s
			                </div>

			            </div>

			            <a href="%s" class="reset-btn">
			                Reset Password
			            </a>

			            <div class="security">
			                For your security, this password reset link is temporary
			                and can only be used once.
			            </div>

			            <div class="warning">
			                If you did not request a password reset, 
			                you can safely also make link expire 
			                Your password will remain unchanged.
			            </div>

			        </div>

			        <div class="footer">
			            This is an automated message. Please do not reply to this email.
			            <br>
			            © 2026 Merkit. All rights reserved.
			        </div>

			    </div>

			</body>

			</html>

						""";

}
