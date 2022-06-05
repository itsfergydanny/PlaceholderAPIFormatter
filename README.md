# PlaceholderAPIFormatter

Ever use a placeholder provided from another PlaceholderAPI compatible plugin that doesn't quite have the number formatting you want? 

To my knowledge, no other solution lets you format another existing placeholder like this.

This plugin lets you configure custom placeholders in a config that looks like this:
```yaml
formats:
  - 'format1:#.##'
  - 'format2:#,###'
  - 'format3:#,###.##'
```

The format is '(format name):(decimalformat)'

Which can then be used in a PlaceholderAPI placeholder as the following: 
```
%papiformatter_<format_name>_<placeholder_to_format>%
```
<br>
For example if I wanted to format the %vault_eco_balance% placeholder, I would use this placeholder to use my format1 format:

```
 %papiformatter_format1_vault_eco_balance%
```

<br>

Hopefully this is clear enough, this was made in like 20 minutes to solve an interesting problem a friend had!

<br>

Check out this article for more information on how to use DecimalFormat: https://jenkov.com/tutorials/java-internationalization/decimalformat.html