import 'package:flutter/material.dart';
import 'package:radiohead/theme/theme.dart';
import 'package:radiohead/widgets/custom_scaffold.dart';
import 'package:radiohead/screens/signup_screen.dart';

class UserInfoScreen extends StatefulWidget {
  const UserInfoScreen({super.key});

  @override
  State<UserInfoScreen> createState() => _UserInfoScreenState();
}

class _UserInfoScreenState extends State<UserInfoScreen> {
  @override
  Widget build(BuildContext context) {
    return CustomScaffold(
      child: Column(
        children: [
          const Expanded(
            flex: 1,
            child: SizedBox(
              height: 10,
              child: CircleAvatar(
                backgroundImage: AssetImage('assets/images/avatar.png'),
                radius: 60,
              ),
            ),
          ),
          Expanded(
            flex: 3,
            child: Container(
              padding: const EdgeInsets.fromLTRB(25.0, 50.0, 25.0, 20.0),
              decoration: const BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(40.0),
                  topRight: Radius.circular(40.0),
                ),
              ),
              child: SingleChildScrollView(
                // get started form
                child: Form(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Text(
                        "User Info",
                        style: TextStyle(
                          fontSize: 30.0,
                          fontWeight: FontWeight.w400,
                          color: lightColorScheme.primary,
                        ),
                      ),
                      Divider(
                        color: lightColorScheme.secondary,
                        height: 9,
                      ),
                      const SizedBox(
                        height: 20,
                      ),
                      Container(
                        height: 200,
                        padding: const EdgeInsets.fromLTRB(25.0, 50.0, 25.0, 20.0),
                        decoration: const BoxDecoration(
                          color: Color.fromRGBO(253, 243, 243, 1.0),
                          borderRadius: BorderRadius.all(
                            Radius.circular(20.0),
                          ),
                        ),
                        child: const Stack(
                          children: [
                          ],
                        )
                      ),
                      const SizedBox(
                        height: 20,
                      ),
                      SizedBox(
                        width: double.infinity,
                        child: ElevatedButton(
                          onPressed: () {
                            ScaffoldMessenger.of(context).showSnackBar(
                              const SnackBar(
                                content: Text('Your Account was deleted'),
                              ),
                            );
                            Navigator.push(
                              context,
                              MaterialPageRoute(
                                builder: (e) => const SignUpScreen(),
                              ),
                            );
                          },
                          style: ElevatedButton.styleFrom(
                            backgroundColor:
                                const Color.fromRGBO(11, 2, 85, 1.0),
                            foregroundColor: Colors.white,
                          ),
                          child: const Text('DELETE ACCOUNT'),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
