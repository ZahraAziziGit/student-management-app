import 'dart:io';

import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:radiohead/screens/home_screen.dart';

import 'package:radiohead/theme/theme.dart';
import 'package:radiohead/screens/signup_screen.dart';

class UserInfoScreen extends StatefulWidget {
  const UserInfoScreen({super.key});

  @override
  State<UserInfoScreen> createState() => _UserInfoScreenState();
}

class _UserInfoScreenState extends State<UserInfoScreen> {
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _confirmedPasswordController =
      TextEditingController();
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _lastnameController = TextEditingController();

  String? _username;
  String? _password;
  String? _name;
  String? _lastname;

  File? _image;

  @override
  void dispose() {
    _passwordController.dispose();
    super.dispose();
  }

  String? _validateName(String? value) {
    if (value == null || value.isEmpty) {
      return "Field must be filled";
    }
    RegExp regex = RegExp(r'^(?=.*[A-Za-z]).+$');
    if (!regex.hasMatch(value)) {
      return "Only use English alphabet";
    }
    return null;
  }

  String? _validatePassword(String? value) {
    if (value == null || value.isEmpty) {
      return "Password must be filled";
    }
    if (_username != null && value.contains(_username!)) {
      return "Password must not contain the username";
    }
    if (value.length < 8) {
      return "password must be more than 8 characters";
    }
    RegExp regex = RegExp(r'^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!?*#@]).+$');
    if (!regex.hasMatch(value)) {
      return "Password must contain:\n"
          "an uppercase letter and a lowercase letter\n"
          "a number and a special character (!?*#@)";
    }
    return null;
  }

  String? _confirmPassword(String? value) {
    if (value == null || value.isEmpty) {
      return "Confirm your password";
    }
    if (value != _password) {
      return "The password confirmation doesn't match";
    }
    return null;
  }

  void _editInformation(BuildContext context) {
    final informationFormKey = GlobalKey<FormState>();

    void resetValues() {
      _nameController.clear();
      _lastnameController.clear();
    }

    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      builder: (BuildContext context) {
        return GestureDetector(
          child: SingleChildScrollView(
            child: Container(
              padding: EdgeInsets.only(
                  bottom: MediaQuery.of(context).viewInsets.bottom),
              child: Form(
                key: informationFormKey,
                child: Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.fromLTRB(30, 20, 30, 10),
                      child: TextFormField(
                        controller: _nameController,
                        decoration: const InputDecoration(labelText: "Name"),
                        validator: _validateName,
                        onChanged: (value) {
                          setState(() {
                            _name = value;
                          });
                        },
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.fromLTRB(30, 10, 30, 10),
                      child: TextFormField(
                        controller: _lastnameController,
                        decoration:
                            const InputDecoration(labelText: "Lastname"),
                        validator: _validateName,
                        onChanged: (value) {
                          setState(() {
                            _lastname = value;
                          });
                        },
                      ),
                    ),
                    const SizedBox(height: 20),
                    SizedBox(
                      width: 150,
                      child: ElevatedButton(
                        style: ElevatedButton.styleFrom(
                          backgroundColor: lightColorScheme.secondary,
                        ),
                        onPressed: () {
                          if (informationFormKey.currentState!.validate()) {
                            ScaffoldMessenger.of(context).showSnackBar(
                              const SnackBar(
                                content: Text("Changes were saved"),
                              ),
                            );
                            Navigator.pop(context);
                            resetValues();
                          }
                        },
                        child: const Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Icon(Icons.check),
                            SizedBox(width: 8),
                            Text("SAVE"),
                          ],
                        ),
                      ),
                    ),
                    const SizedBox(height: 20),
                  ],
                ),
              ),
            ),
          ),
        );
      },
    ).whenComplete(() {
      resetValues();
    });
  }

  void _changePassword(BuildContext context) {
    final passwordFormKey = GlobalKey<FormState>();
    bool isPasswordVisible = false;
    bool isConfirmedPasswordVisible = false;

    void resetValues() {
      _passwordController.clear();
      _confirmedPasswordController.clear();
      isPasswordVisible = false;
      isConfirmedPasswordVisible = false;
    }

    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      builder: (BuildContext context) {
        return StatefulBuilder(
          builder: (BuildContext context, StateSetter setState) {
            return GestureDetector(
              child: SingleChildScrollView(
                child: Container(
                  padding: EdgeInsets.only(
                      bottom: MediaQuery.of(context).viewInsets.bottom),
                  child: Form(
                    key: passwordFormKey,
                    child: Column(
                      children: [
                        Padding(
                          padding: const EdgeInsets.fromLTRB(30, 20, 30, 10),
                          child: TextFormField(
                            controller: _passwordController,
                            obscureText: !isPasswordVisible,
                            obscuringCharacter: '*',
                            decoration: InputDecoration(
                              labelText: "New password",
                              suffixIcon: IconButton(
                                icon: Icon(
                                  isPasswordVisible
                                      ? Icons.visibility
                                      : Icons.visibility_off,
                                ),
                                onPressed: () {
                                  setState(() {
                                    isPasswordVisible = !isPasswordVisible;
                                  });
                                },
                              ),
                            ),
                            validator: _validatePassword,
                            onChanged: (value) {
                              setState(() {
                                _password = value;
                              });
                            },
                          ),
                        ),
                        Padding(
                          padding: const EdgeInsets.fromLTRB(30, 20, 30, 10),
                          child: TextFormField(
                            controller: _confirmedPasswordController,
                            obscureText: !isConfirmedPasswordVisible,
                            obscuringCharacter: '*',
                            decoration: InputDecoration(
                              labelText: "Confirm password",
                              suffixIcon: IconButton(
                                icon: Icon(
                                  isConfirmedPasswordVisible
                                      ? Icons.visibility
                                      : Icons.visibility_off,
                                ),
                                onPressed: () {
                                  setState(() {
                                    isConfirmedPasswordVisible =
                                        !isConfirmedPasswordVisible;
                                  });
                                },
                              ),
                            ),
                            validator: _confirmPassword,
                          ),
                        ),
                        const SizedBox(height: 20),
                        SizedBox(
                          width: 220,
                          child: ElevatedButton(
                            style: ElevatedButton.styleFrom(
                              backgroundColor: lightColorScheme.secondary,
                            ),
                            onPressed: () {
                              if (passwordFormKey.currentState!.validate()) {
                                ScaffoldMessenger.of(context).showSnackBar(
                                  const SnackBar(
                                    content: Text("Password was changed"),
                                  ),
                                );
                                Navigator.pop(context);
                                resetValues();
                              }
                            },
                            child: const Row(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: [
                                Icon(Icons.key),
                                SizedBox(width: 8),
                                Text("CHANGE PASSWORD"),
                              ],
                            ),
                          ),
                        ),
                        const SizedBox(height: 20),
                      ],
                    ),
                  ),
                ),
              ),
            );
          },
        );
      },
    ).whenComplete(() {
      resetValues();
    });
  }

  Future<void> _pickImage() async {
    final ImagePicker picker = ImagePicker();
    final XFile? image = await picker.pickImage(source: ImageSource.gallery);

    if (image != null) {
      setState(() {
        _image = File(image.path);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        iconTheme: const IconThemeData(color: Colors.white),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pushAndRemoveUntil(
              context,
              MaterialPageRoute(builder: (context) => const HomeScreen()),
              (Route<dynamic> route) => false,
            );
          },
        ),
        backgroundColor: lightColorScheme.primary,
        elevation: 0,
      ),
      body: Container(
        color: lightColorScheme.primary,
        child: Column(
          children: [
            Container(
              width: double.infinity,
              color: lightColorScheme.primary,
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 10.0),
                child: Column(
                  children: [
                    Stack(
                      children: [
                        CircleAvatar(
                          radius: 50,
                          backgroundColor: Colors.white,
                          child: CircleAvatar(
                            radius: 45,
                            backgroundColor: Colors.grey,
                            backgroundImage:
                                _image != null ? FileImage(_image!) : null,
                            child: _image == null
                                ? const Icon(Icons.person, size: 67)
                                : null,
                          ),
                        ),
                        Positioned(
                          bottom: 0,
                          right: 0,
                          child: GestureDetector(
                            onTap: _pickImage,
                            child: const CircleAvatar(
                              radius: 15,
                              backgroundColor: Colors.black87,
                              child: Icon(Icons.camera_alt_outlined, size: 15),
                            ),
                          ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 10),
                    Text(
                      _lastname == null ? " " : "$_name $_lastname",
                      style: const TextStyle(
                        fontFamily: 'Georgia',
                        color: Colors.white,
                        fontSize: 23,
                        fontWeight: FontWeight.w500,
                      ),
                    ),
                    const Text(
                      "Student",
                      style: TextStyle(
                        fontFamily: 'Georgia',
                        color: Colors.white54,
                        fontSize: 17,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            Expanded(
              child: Container(
                margin: const EdgeInsets.all(0),
                padding: const EdgeInsets.fromLTRB(20, 20, 20, 10),
                decoration: const BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(30.0),
                    topRight: Radius.circular(30.0),
                  ),
                ),
                child: Column(
                  children: [
                    const Card(
                      child: Padding(
                        padding: EdgeInsets.fromLTRB(5, 7, 5, 7),
                        child: Column(
                          children: [
                            SizedBox(
                              height: 45,
                              child: ListTile(
                                title: Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    Text(
                                      "Student ID",
                                      style: TextStyle(
                                        fontFamily: 'Verdana',
                                        fontWeight: FontWeight.bold,
                                      ),
                                      textAlign: TextAlign.left,
                                    ),
                                    Text(
                                      "402243000",
                                      style: TextStyle(
                                        fontFamily: 'Verdana',
                                        color: Colors.grey,
                                      ),
                                      textAlign: TextAlign.right,
                                    ),
                                  ],
                                ),
                                contentPadding:
                                    EdgeInsets.symmetric(horizontal: 20),
                              ),
                            ),
                            Divider(
                              endIndent: 20,
                              indent: 20,
                            ),
                            SizedBox(
                              height: 45,
                              child: ListTile(
                                title: Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    Text(
                                      "Current term",
                                      style: TextStyle(
                                        fontFamily: 'Verdana',
                                        fontWeight: FontWeight.bold,
                                      ),
                                      textAlign: TextAlign.left,
                                    ),
                                    Text(
                                      "2024 - Spring",
                                      style: TextStyle(
                                        fontFamily: 'Verdana',
                                        color: Colors.grey,
                                      ),
                                      textAlign: TextAlign.right,
                                    ),
                                  ],
                                ),
                                contentPadding:
                                    EdgeInsets.symmetric(horizontal: 20),
                              ),
                            ),
                            Divider(
                              endIndent: 20,
                              indent: 20,
                            ),
                            SizedBox(
                              height: 45,
                              child: ListTile(
                                title: Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    Text(
                                      "Units count",
                                      style: TextStyle(
                                        fontFamily: 'Verdana',
                                        fontWeight: FontWeight.bold,
                                      ),
                                      textAlign: TextAlign.left,
                                    ),
                                    Text(
                                      "16",
                                      style: TextStyle(
                                        fontFamily: 'Verdana',
                                        color: Colors.grey,
                                      ),
                                      textAlign: TextAlign.right,
                                    ),
                                  ],
                                ),
                                contentPadding:
                                    EdgeInsets.symmetric(horizontal: 20),
                              ),
                            ),
                            Divider(
                              endIndent: 20,
                              indent: 20,
                            ),
                            SizedBox(
                              height: 45,
                              child: ListTile(
                                title: Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    Text(
                                      "Total average",
                                      style: TextStyle(
                                        fontFamily: 'Verdana',
                                        fontWeight: FontWeight.bold,
                                      ),
                                      textAlign: TextAlign.left,
                                    ),
                                    Text(
                                      "16.54",
                                      style: TextStyle(
                                        fontFamily: 'Verdana',
                                        color: Colors.grey,
                                      ),
                                      textAlign: TextAlign.right,
                                    ),
                                  ],
                                ),
                                contentPadding:
                                    EdgeInsets.symmetric(horizontal: 20),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Card(
                      child: Padding(
                        padding: const EdgeInsets.fromLTRB(0, 5, 5, 5),
                        child: Column(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            SizedBox(
                              height: 50,
                              child: ListTile(
                                leading: CircleAvatar(
                                  radius: 15,
                                  backgroundColor: lightColorScheme.secondary,
                                  child: const Icon(Icons.edit_rounded,
                                      color: Colors.white, size: 20),
                                ),
                                title: const Text("Edit your profile"),
                                onTap: () {
                                  _editInformation(context);
                                },
                              ),
                            ),
                            const Divider(
                              endIndent: 20,
                              indent: 20,
                            ),
                            SizedBox(
                              height: 50,
                              child: ListTile(
                                leading: CircleAvatar(
                                  radius: 15,
                                  backgroundColor: lightColorScheme.secondary,
                                  child: const Icon(Icons.lock_outlined,
                                      color: Colors.white, size: 20),
                                ),
                                title: const Text("Change your password"),
                                onTap: () {
                                  _changePassword(context);
                                },
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    SizedBox(
                      width: 360,
                      child: ElevatedButton(
                        onPressed: () {
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(
                              content: Text("Account was deleted"),
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
                          backgroundColor: Colors.red,
                        ),
                        child: const Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Icon(Icons.delete),
                            SizedBox(width: 8),
                            Text("DELETE ACCOUNT"),
                          ],
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
