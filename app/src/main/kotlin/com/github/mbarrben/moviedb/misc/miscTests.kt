package com.github.mbarrben.moviedb.misc

fun emojiFlag(iso_3166_1: String): String {
  check(iso_3166_1.length == 2) { "ISO 3166 1 specifies codes have 2 characters" }

  val scalars = mapOf('A' to "🇦",
                      'B' to "🇧",
                      'C' to "🇨",
                      'D' to "🇩",
                      'E' to "🇪",
                      'F' to "🇫",
                      'G' to "🇬",
                      'H' to "🇭",
                      'I' to "🇮",
                      'J' to "🇯",
                      'K' to "🇰",
                      'L' to "🇱",
                      'M' to "🇲",
                      'N' to "🇳",
                      'O' to "🇴",
                      'P' to "🇵",
                      'Q' to "🇶",
                      'R' to "🇷",
                      'S' to "🇸",
                      'T' to "🇹",
                      'U' to "🇺",
                      'V' to "🇻",
                      'W' to "🇼",
                      'X' to "🇽",
                      'Y' to "🇾",
                      'Z' to "🇿"
  )

  return iso_3166_1.map { scalars[it] }
      .reduce { l, r -> l + r }
      .toString()
}