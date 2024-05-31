import 'package:flutter/material.dart';
import 'package:radiohead/theme/theme.dart';
import 'package:radiohead/widgets/custom_scaffold.dart';
import 'package:radiohead/widgets/welcome_button.dart';
import 'package:radiohead/screens/signup_screen.dart';
import 'package:radiohead/screens/login_screen.dart';


class WelcomeScreen extends StatelessWidget {
  const WelcomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return CustomScaffold(
      child: Column(
        children: [
          Flexible(
            flex: 6,
            child: Container(
              padding: const EdgeInsets.symmetric(
                vertical: 0,
                horizontal: 40.0,
              ),
              child: Center(
                child: RichText(
                  textAlign: TextAlign.center,
                  text: const TextSpan(
                    children: [
                      TextSpan(
                        text: "خوش آمدید",
                        style: TextStyle(
                          fontSize: 50.0,
                          fontWeight: FontWeight.w500,
                          color: Colors.white,
                        ),
                      ),
                      TextSpan(
                        text: '\n\n\n'
                      ),
                      TextSpan(
                        text: "برای ورود به اپلیکیشن دانشجویار، انتخاب کنید",
                        style: TextStyle(
                          fontSize: 30.0,
                          fontWeight: FontWeight.normal,
                          color: Colors.white70,
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ),
          Flexible(
            flex: 1,
            child: Align(
              alignment: Alignment.bottomLeft,
              child: Row(
                children: [
                  const Expanded(
                      child: WelcomeButton(
                    buttonText: "ورود",
                    onTap: LogInScreen(),
                    color: Colors.transparent,
                    textColor: Colors.white,
                  )),
                  Expanded(
                    child: WelcomeButton(
                      buttonText: 'ثبت نام',
                      onTap: const SignUpScreen(),
                      color: Colors.white,
                      textColor: lightColorScheme.primary,
                    ),
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
