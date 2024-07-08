import 'package:flutter/material.dart';

import 'package:url_launcher/url_launcher.dart';

class NewsCard extends StatelessWidget {
  final String newsTitle;
  final String newsText;
  final String link;
  final Uri uri;
  final String image;

  const NewsCard({
    super.key,
    required this.newsTitle,
    required this.newsText,
    required this.link,
    required this.uri,
    required this.image,
  });

  Future<void> _launchUrl(Uri uri) async {
    if (!await launchUrl(uri, mode: LaunchMode.inAppWebView)) {
      throw Exception('Could not launch $uri');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(top: 10),
      width: MediaQuery.of(context).size.width,
      height: 200,
      decoration: BoxDecoration(
        color: Colors.indigo,
        borderRadius: BorderRadius.circular(20.0),
        border: Border.all(width: 2, color: Colors.white),
      ),
      child: Row(
        children: [
          SizedBox(
            width: MediaQuery.of(context).size.width / 2,
            child: Column(
              children: [
                Padding(
                  padding: const EdgeInsets.fromLTRB(20, 10, 20, 10),
                  child: Text(
                    newsTitle,
                    textAlign: TextAlign.center,
                    style: const TextStyle(
                        fontSize: 16,
                        color: Colors.white,
                        fontFamily: 'Times New Roman'),
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.fromLTRB(15, 0, 15, 5),
                  child: Text(
                    newsText,
                    style: const TextStyle(
                        fontSize: 14,
                        color: Colors.white70,
                        fontFamily: 'Times New Roman'),
                  ),
                ),
                TextButton(
                  onPressed: () => _launchUrl(uri),
                  child: const Text(
                    "Read more...",
                    style: TextStyle(color: Colors.purpleAccent, fontSize: 12),
                  ),
                ),
              ],
            ),
          ),
          ClipRRect(
            borderRadius: const BorderRadius.only(
              topRight: Radius.circular(16),
              bottomRight: Radius.circular(16),
            ),
            child: Image(
                image: AssetImage(image),
                width: MediaQuery.of(context).size.width / 2 - 44,
                height: 200,
                fit: BoxFit.cover),
          ),
        ],
      ),
    );
  }
}
