/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/
package org.apache.cayenne.modeler.dialog.templateeditor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.AbstractJFlexCTokenMaker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;


/**
 * @since 5.0
 */
public class VelocityTokenMaker extends AbstractJFlexCTokenMaker {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int EOL_COMMENT = 2;
  public static final int YYINITIAL = 0;
  public static final int MLC = 1;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\21\1\10\1\0\1\21\1\17\22\0\1\21\1\43\1\15"+
    "\1\20\1\1\1\47\1\45\1\7\2\44\1\22\1\46\1\41\1\26"+
    "\1\24\1\50\1\4\3\16\4\6\2\3\1\54\1\41\1\71\1\70"+
    "\1\72\1\43\1\42\1\35\1\56\1\5\1\60\1\25\1\33\1\65"+
    "\1\51\1\53\1\67\1\57\1\36\1\64\1\61\1\63\1\52\1\1"+
    "\1\31\1\37\1\27\1\12\1\62\1\55\1\23\1\66\1\1\1\44"+
    "\1\11\1\44\1\73\1\2\1\0\1\35\1\14\1\5\1\60\1\25"+
    "\1\34\1\65\1\75\1\53\1\67\1\57\1\36\1\64\1\13\1\63"+
    "\1\52\1\1\1\32\1\37\1\30\1\12\1\62\1\76\1\23\1\66"+
    "\1\1\1\40\1\74\1\40\1\43\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\2\1\2\2\1\3\1\4\1\1\1\5\1\1"+
    "\1\6\1\7\2\1\1\7\3\1\1\10\3\7\2\1"+
    "\3\7\1\11\1\12\10\11\1\13\6\11\1\0\1\14"+
    "\1\0\2\14\1\3\1\15\1\0\1\3\1\1\2\5"+
    "\1\16\1\0\1\17\1\20\7\0\1\21\7\1\1\22"+
    "\2\11\2\0\2\11\2\0\2\11\2\0\2\11\3\0"+
    "\1\21\1\0\1\23\1\3\1\24\2\3\1\15\1\3"+
    "\1\1\1\5\1\25\1\5\11\0\1\26\2\0\10\1"+
    "\2\11\2\0\2\11\2\0\2\11\2\0\2\11\3\0"+
    "\1\3\1\5\11\0\1\1\1\27\6\1\1\11\1\0"+
    "\2\11\1\30\1\0\1\11\1\0\2\11\1\31\2\0"+
    "\1\3\1\5\1\0\1\26\6\0\6\1\1\11\2\0"+
    "\1\11\3\0\1\3\1\5\4\0\3\1\1\32\2\0"+
    "\14\1";

  private static int [] zzUnpackAction() {
    int [] result = new int[224];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\77\0\176\0\275\0\374\0\u013b\0\u017a\0\u01b9"+
    "\0\275\0\u01f8\0\u0237\0\u0276\0\u02b5\0\u02f4\0\u0333\0\u0372"+
    "\0\u03b1\0\u03f0\0\u042f\0\u046e\0\275\0\275\0\u04ad\0\u04ec"+
    "\0\u052b\0\u056a\0\u05a9\0\u05e8\0\u0627\0\u0666\0\275\0\u06a5"+
    "\0\u06e4\0\u0723\0\u0762\0\u07a1\0\u07e0\0\u081f\0\u085e\0\275"+
    "\0\u089d\0\u08dc\0\u091b\0\u095a\0\u0999\0\u09d8\0\u0a17\0\u0a56"+
    "\0\u0333\0\u0a95\0\u0ad4\0\u0b13\0\275\0\u0b52\0\u0b91\0\u0bd0"+
    "\0\u0c0f\0\u0c4e\0\275\0\u0c8d\0\275\0\275\0\u0ccc\0\u0d0b"+
    "\0\u0d4a\0\u0d89\0\u0dc8\0\u0e07\0\u0e46\0\u0e85\0\u0ec4\0\u0f03"+
    "\0\u0f42\0\u0f81\0\u0fc0\0\u0fff\0\u103e\0\275\0\u107d\0\u10bc"+
    "\0\u10fb\0\u113a\0\u1179\0\u11b8\0\u11f7\0\u1236\0\u1275\0\u12b4"+
    "\0\u12f3\0\u1332\0\u1371\0\u13b0\0\u13ef\0\u142e\0\u146d\0\u14ac"+
    "\0\u14eb\0\u0ad4\0\u152a\0\275\0\u1569\0\u15a8\0\u0b52\0\u15e7"+
    "\0\u1626\0\u1665\0\275\0\u16a4\0\u16e3\0\u1722\0\u1761\0\u17a0"+
    "\0\u17df\0\u181e\0\u185d\0\u189c\0\u18db\0\275\0\u191a\0\u1959"+
    "\0\u1998\0\u19d7\0\u1a16\0\u1a55\0\u1a94\0\u1ad3\0\u1b12\0\u1b51"+
    "\0\u1b90\0\u1bcf\0\u1c0e\0\u1c4d\0\u1c8c\0\u1ccb\0\u1d0a\0\u1d49"+
    "\0\u1d88\0\u1dc7\0\u1e06\0\u1e45\0\u1e84\0\u1ec3\0\u1f02\0\u1f41"+
    "\0\u1f80\0\u1fbf\0\u1ffe\0\u203d\0\u207c\0\u20bb\0\u20fa\0\u2139"+
    "\0\u2178\0\u21b7\0\u21f6\0\u2235\0\u2274\0\374\0\u22b3\0\u22f2"+
    "\0\u2331\0\u2370\0\u23af\0\u23ee\0\u242d\0\u246c\0\u24ab\0\u24ea"+
    "\0\u2529\0\u2568\0\u25a7\0\u25e6\0\u2625\0\u2664\0\u26a3\0\u26e2"+
    "\0\u2721\0\u2760\0\u279f\0\u27de\0\u281d\0\u285c\0\u289b\0\u28da"+
    "\0\u2919\0\u2958\0\u2997\0\u29d6\0\u2a15\0\u2a54\0\u2a93\0\u2ad2"+
    "\0\u2b11\0\u2b50\0\u2b8f\0\u2529\0\u2bce\0\u2c0d\0\u26a3\0\u2c4c"+
    "\0\u2c8b\0\u2cca\0\u2d09\0\u2d48\0\u2d87\0\u2dc6\0\u2e05\0\u2e44"+
    "\0\u2e83\0\374\0\u2ec2\0\u2f01\0\u2f40\0\u2f7f\0\u2fbe\0\u2ffd"+
    "\0\u303c\0\u307b\0\u30ba\0\u30f9\0\u3138\0\u3177\0\u31b6\0\u31f5";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[224];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\2\5\1\6\1\7\1\5\1\6\1\10\1\11"+
    "\1\4\2\5\1\12\1\13\1\6\1\4\1\14\1\15"+
    "\1\16\1\5\1\17\1\20\1\21\2\22\2\5\2\23"+
    "\2\5\1\24\1\25\2\4\1\26\1\25\1\27\1\30"+
    "\2\16\2\5\1\31\1\26\1\5\1\12\4\5\1\32"+
    "\4\5\1\16\1\33\1\34\1\26\1\35\2\5\10\36"+
    "\1\37\11\36\1\40\10\36\1\41\1\42\14\36\1\43"+
    "\3\36\1\44\17\36\1\45\1\46\10\47\1\50\22\47"+
    "\1\51\1\52\14\47\1\53\3\47\1\54\17\47\1\55"+
    "\1\56\100\0\6\5\2\0\1\57\3\5\1\0\1\5"+
    "\4\0\1\5\1\0\1\5\1\0\11\5\11\0\3\5"+
    "\1\0\13\5\5\0\2\5\3\60\2\6\1\60\1\6"+
    "\2\0\4\60\1\0\1\6\1\0\1\60\2\0\1\60"+
    "\1\61\1\62\1\0\11\60\2\0\1\60\6\0\3\60"+
    "\1\0\13\60\5\0\5\60\2\6\1\60\1\6\2\0"+
    "\4\60\1\0\1\6\1\0\1\60\2\0\1\63\1\61"+
    "\1\62\1\0\11\60\2\0\1\60\6\0\3\60\1\0"+
    "\13\60\5\0\2\60\7\64\1\65\1\66\1\67\65\64"+
    "\1\0\6\5\2\0\1\57\3\5\1\0\1\5\4\0"+
    "\1\5\1\0\1\5\1\0\6\5\1\70\2\5\11\0"+
    "\3\5\1\0\13\5\5\0\2\5\10\13\1\71\1\72"+
    "\3\13\1\73\61\13\14\0\1\74\3\0\1\75\1\0"+
    "\1\76\2\0\1\77\5\0\2\100\2\0\1\101\12\0"+
    "\1\102\1\103\2\0\1\74\1\0\1\104\3\0\1\105"+
    "\33\0\1\15\145\0\1\26\11\0\2\106\1\0\1\106"+
    "\7\0\1\106\61\0\6\5\2\0\1\57\1\5\1\107"+
    "\1\5\1\0\1\5\4\0\1\5\1\0\1\5\1\0"+
    "\11\5\11\0\3\5\1\0\4\5\1\107\6\5\5\0"+
    "\2\5\26\0\1\26\41\0\1\26\7\0\6\5\2\0"+
    "\1\57\3\5\1\0\1\5\4\0\1\5\1\0\1\5"+
    "\1\0\2\5\2\110\5\5\11\0\3\5\1\0\13\5"+
    "\5\0\2\5\1\0\6\5\2\0\1\57\3\5\1\0"+
    "\1\5\4\0\1\5\1\0\1\5\1\0\6\5\1\111"+
    "\2\5\11\0\3\5\1\0\13\5\5\0\2\5\1\0"+
    "\6\5\2\0\1\57\1\112\2\5\1\0\1\5\4\0"+
    "\1\5\1\0\1\5\1\0\2\113\7\5\11\0\3\5"+
    "\1\0\13\5\5\0\2\5\45\0\1\26\77\0\1\26"+
    "\21\0\1\26\7\0\6\5\2\0\1\57\3\5\1\0"+
    "\1\5\4\0\1\5\1\0\1\5\1\0\11\5\11\0"+
    "\3\5\1\0\7\5\1\114\3\5\5\0\2\5\1\0"+
    "\6\5\2\0\1\57\2\5\1\115\1\0\1\5\4\0"+
    "\1\5\1\0\1\5\1\0\11\5\11\0\3\5\1\0"+
    "\1\5\1\115\11\5\5\0\2\5\71\0\1\16\77\0"+
    "\1\16\100\0\1\26\2\0\10\36\1\0\11\36\1\0"+
    "\11\36\1\0\40\36\22\0\1\116\56\0\10\36\1\0"+
    "\11\36\1\0\4\36\2\117\3\36\1\0\16\36\1\120"+
    "\21\36\31\0\2\121\22\0\1\122\23\0\10\36\1\0"+
    "\11\36\1\0\4\36\2\123\3\36\1\0\40\36\2\0"+
    "\10\36\1\0\11\36\1\0\11\36\1\0\20\36\1\124"+
    "\17\36\1\0\1\125\27\0\2\126\123\0\1\125\20\0"+
    "\1\125\10\47\1\0\23\47\1\0\40\47\2\0\10\47"+
    "\1\0\16\47\2\127\3\47\1\0\16\47\1\130\21\47"+
    "\31\0\2\131\22\0\1\132\23\0\10\47\1\0\16\47"+
    "\2\133\3\47\1\0\40\47\2\0\10\47\1\0\23\47"+
    "\1\0\20\47\1\134\17\47\1\0\1\135\27\0\2\136"+
    "\123\0\1\135\20\0\1\135\12\0\1\137\64\0\7\60"+
    "\2\0\4\60\1\0\1\60\1\0\1\60\2\0\1\60"+
    "\1\0\1\60\1\0\11\60\2\0\1\60\6\0\3\60"+
    "\1\0\13\60\5\0\5\60\2\140\1\60\1\140\2\0"+
    "\4\60\1\0\1\140\1\0\1\60\2\0\1\60\1\0"+
    "\1\60\1\141\11\60\2\0\1\60\3\0\1\141\2\0"+
    "\3\60\1\0\13\60\5\0\5\60\4\142\2\0\3\60"+
    "\1\142\1\0\1\142\1\0\1\60\2\0\1\60\1\0"+
    "\1\142\1\0\4\60\3\142\2\60\2\0\1\60\6\0"+
    "\3\60\1\0\1\60\1\142\1\60\1\142\7\60\5\0"+
    "\2\60\7\143\1\144\1\0\66\143\7\0\1\144\67\0"+
    "\4\143\1\145\1\143\1\146\1\147\1\0\1\64\1\150"+
    "\3\64\1\145\11\143\1\64\1\143\1\64\1\143\1\64"+
    "\42\143\1\0\6\5\2\0\1\57\3\5\1\0\1\5"+
    "\4\0\1\5\1\0\1\5\1\0\10\5\1\151\11\0"+
    "\3\5\1\0\13\5\5\0\2\5\11\71\1\152\3\71"+
    "\1\153\65\71\1\13\1\71\2\13\1\0\1\13\1\154"+
    "\4\13\11\71\1\13\1\71\1\13\1\71\1\13\42\71"+
    "\31\0\2\155\57\0\1\156\22\0\1\157\22\0\1\156"+
    "\1\160\77\0\1\161\40\0\1\162\1\0\2\163\103\0"+
    "\1\164\54\0\1\165\17\0\2\166\24\0\1\165\42\0"+
    "\1\167\106\0\1\170\41\0\3\60\2\106\1\60\1\106"+
    "\2\0\4\60\1\0\1\106\1\0\1\60\2\0\1\60"+
    "\1\0\1\62\1\0\11\60\2\0\1\60\6\0\3\60"+
    "\1\0\13\60\5\0\2\60\1\0\6\5\2\0\1\57"+
    "\3\5\1\0\1\5\4\0\1\5\1\0\1\5\1\0"+
    "\2\171\7\5\11\0\3\5\1\0\13\5\5\0\2\5"+
    "\1\0\6\5\2\0\1\57\1\172\2\5\1\0\1\5"+
    "\4\0\1\5\1\0\1\5\1\0\11\5\11\0\3\5"+
    "\1\0\13\5\5\0\2\5\1\0\6\5\2\0\1\57"+
    "\3\5\1\0\1\5\4\0\1\5\1\0\1\5\1\0"+
    "\7\5\1\173\1\5\11\0\3\5\1\0\13\5\5\0"+
    "\2\5\1\0\6\5\2\0\1\57\2\5\1\174\1\0"+
    "\1\5\4\0\1\5\1\0\1\5\1\0\11\5\11\0"+
    "\1\5\1\175\1\5\1\0\1\5\1\174\11\5\5\0"+
    "\2\5\1\0\6\5\2\0\1\57\3\5\1\0\1\5"+
    "\4\0\1\5\1\0\1\5\1\0\2\5\2\176\5\5"+
    "\11\0\3\5\1\0\13\5\5\0\2\5\1\0\6\5"+
    "\2\0\1\57\3\5\1\0\1\5\4\0\1\5\1\0"+
    "\1\5\1\0\11\5\11\0\1\5\1\177\1\5\1\0"+
    "\13\5\5\0\2\5\1\0\6\5\2\0\1\57\3\5"+
    "\1\0\1\5\4\0\1\5\1\0\1\5\1\0\11\5"+
    "\11\0\3\5\1\0\12\5\1\200\5\0\2\5\10\36"+
    "\1\0\11\36\1\0\11\36\1\0\15\36\1\201\22\36"+
    "\2\0\10\36\1\0\11\36\1\0\11\36\1\0\1\36"+
    "\1\202\36\36\54\0\1\203\62\0\1\204\40\0\10\36"+
    "\1\0\11\36\1\0\4\36\2\205\3\36\1\0\40\36"+
    "\2\0\10\36\1\0\11\36\1\0\11\36\1\0\20\36"+
    "\1\206\17\36\1\0\1\207\55\0\1\207\20\0\1\207"+
    "\27\0\2\210\46\0\10\47\1\0\23\47\1\0\15\47"+
    "\1\211\22\47\2\0\10\47\1\0\23\47\1\0\1\47"+
    "\1\212\36\47\54\0\1\213\62\0\1\214\40\0\10\47"+
    "\1\0\16\47\2\215\3\47\1\0\40\47\2\0\10\47"+
    "\1\0\23\47\1\0\20\47\1\216\17\47\1\0\1\217"+
    "\55\0\1\217\20\0\1\217\27\0\2\220\51\0\4\221"+
    "\5\0\1\221\1\0\1\221\6\0\1\221\5\0\3\221"+
    "\20\0\1\221\1\0\1\221\16\0\3\60\2\140\1\60"+
    "\1\140\2\0\4\60\1\0\1\140\1\0\1\60\2\0"+
    "\1\60\1\0\1\60\1\0\11\60\2\0\1\60\6\0"+
    "\3\60\1\0\13\60\5\0\2\60\3\0\2\140\1\0"+
    "\1\140\7\0\1\140\60\0\7\143\1\65\1\0\72\143"+
    "\1\146\1\143\1\146\1\144\1\0\5\143\1\146\64\143"+
    "\1\64\1\143\1\64\1\144\1\0\5\143\1\64\63\143"+
    "\4\222\1\65\1\0\3\143\1\222\1\143\1\222\6\143"+
    "\1\222\5\143\3\222\20\143\1\222\1\143\1\222\16\143"+
    "\1\0\6\5\2\0\1\57\3\5\1\0\1\5\4\0"+
    "\1\5\1\0\1\174\1\0\11\5\11\0\3\5\1\0"+
    "\13\5\5\0\2\5\10\71\1\0\71\71\4\223\2\71"+
    "\1\152\2\71\1\223\1\153\1\223\6\71\1\223\5\71"+
    "\3\223\20\71\1\223\1\71\1\223\16\71\25\0\1\224"+
    "\131\0\1\166\55\0\1\225\74\0\1\226\72\0\2\227"+
    "\73\0\2\166\131\0\1\230\44\0\2\231\51\0\1\232"+
    "\124\0\2\233\47\0\1\234\72\0\6\5\2\0\1\57"+
    "\3\5\1\0\1\5\4\0\1\5\1\0\1\5\1\0"+
    "\11\5\11\0\2\5\1\235\1\0\13\5\5\0\2\5"+
    "\1\0\6\5\2\0\1\57\3\5\1\0\1\5\4\0"+
    "\1\5\1\0\1\236\1\0\11\5\11\0\3\5\1\0"+
    "\13\5\5\0\2\5\1\0\6\5\2\0\1\57\3\5"+
    "\1\0\1\5\4\0\1\5\1\0\1\5\1\0\10\5"+
    "\1\172\11\0\3\5\1\0\13\5\5\0\2\5\1\0"+
    "\4\5\1\237\1\5\2\0\1\57\3\5\1\0\1\5"+
    "\4\0\1\5\1\0\1\5\1\0\11\5\11\0\1\5"+
    "\1\240\1\5\1\0\13\5\5\0\2\5\1\0\6\5"+
    "\2\0\1\57\3\5\1\0\1\5\4\0\1\5\1\0"+
    "\1\241\1\0\11\5\11\0\3\5\1\0\13\5\5\0"+
    "\2\5\1\0\6\5\2\0\1\57\3\5\1\0\1\5"+
    "\4\0\1\5\1\0\1\5\1\0\11\5\11\0\2\5"+
    "\1\242\1\0\13\5\5\0\2\5\1\0\6\5\2\0"+
    "\1\57\3\5\1\0\1\5\4\0\1\5\1\0\1\5"+
    "\1\0\11\5\11\0\3\5\1\0\6\5\1\243\4\5"+
    "\5\0\2\5\1\0\6\5\2\0\1\57\3\5\1\0"+
    "\1\5\4\0\1\5\1\0\1\244\1\0\11\5\11\0"+
    "\3\5\1\0\13\5\5\0\2\5\10\36\1\0\11\36"+
    "\1\0\11\36\1\0\17\36\1\245\20\36\2\0\10\36"+
    "\1\0\11\36\1\0\2\36\1\201\6\36\1\0\40\36"+
    "\56\0\1\246\47\0\1\203\51\0\10\36\1\0\11\36"+
    "\1\0\11\36\1\0\15\36\1\247\22\36\2\0\10\36"+
    "\1\0\11\36\1\0\1\36\1\250\7\36\1\0\40\36"+
    "\26\0\1\251\124\0\1\252\24\0\10\47\1\0\23\47"+
    "\1\0\17\47\1\253\20\47\2\0\10\47\1\0\14\47"+
    "\1\211\6\47\1\0\40\47\56\0\1\254\47\0\1\213"+
    "\51\0\10\47\1\0\23\47\1\0\15\47\1\255\22\47"+
    "\2\0\10\47\1\0\13\47\1\256\7\47\1\0\40\47"+
    "\26\0\1\257\124\0\1\260\27\0\4\261\5\0\1\261"+
    "\1\0\1\261\6\0\1\261\5\0\3\261\20\0\1\261"+
    "\1\0\1\261\16\0\3\143\4\262\1\65\1\0\3\143"+
    "\1\262\1\143\1\262\6\143\1\262\5\143\3\262\20\143"+
    "\1\262\1\143\1\262\16\143\3\71\4\263\2\71\1\152"+
    "\2\71\1\263\1\153\1\263\6\71\1\263\5\71\3\263"+
    "\20\71\1\263\1\71\1\263\16\71\35\0\1\264\66\0"+
    "\1\265\107\0\1\266\65\0\1\267\123\0\1\166\63\0"+
    "\1\270\75\0\1\271\113\0\1\272\54\0\2\273\45\0"+
    "\6\5\2\0\1\57\3\5\1\0\1\5\4\0\1\5"+
    "\1\0\1\5\1\0\2\274\7\5\11\0\3\5\1\0"+
    "\13\5\5\0\2\5\1\0\6\5\2\0\1\57\3\5"+
    "\1\0\1\5\4\0\1\5\1\0\1\5\1\0\7\5"+
    "\1\275\1\5\11\0\3\5\1\0\13\5\5\0\2\5"+
    "\1\0\6\5\2\0\1\57\3\5\1\0\1\5\4\0"+
    "\1\5\1\0\1\5\1\0\6\5\1\276\2\5\11\0"+
    "\3\5\1\0\13\5\5\0\2\5\1\0\6\5\2\0"+
    "\1\57\3\5\1\0\1\5\4\0\1\5\1\0\1\5"+
    "\1\0\2\5\2\174\5\5\11\0\3\5\1\0\13\5"+
    "\5\0\2\5\1\0\6\5\2\0\1\57\1\5\1\277"+
    "\1\5\1\0\1\5\4\0\1\5\1\0\1\5\1\0"+
    "\11\5\11\0\3\5\1\0\4\5\1\277\6\5\5\0"+
    "\2\5\1\0\6\5\2\0\1\57\3\5\1\0\1\5"+
    "\4\0\1\5\1\0\1\5\1\0\2\5\2\300\5\5"+
    "\11\0\3\5\1\0\13\5\5\0\2\5\1\0\4\5"+
    "\1\301\1\5\2\0\1\57\3\5\1\0\1\5\4\0"+
    "\1\5\1\0\1\5\1\0\11\5\11\0\3\5\1\0"+
    "\13\5\5\0\2\5\10\36\1\0\11\36\1\0\11\36"+
    "\1\0\13\36\1\302\24\36\52\0\1\303\26\0\10\36"+
    "\1\0\11\36\1\0\11\36\1\0\2\36\1\201\14\36"+
    "\1\245\20\36\2\0\1\36\7\250\1\0\1\36\3\250"+
    "\1\36\1\250\1\36\1\250\1\36\1\304\11\250\1\251"+
    "\3\250\1\36\30\250\4\36\2\251\1\0\1\251\1\304"+
    "\4\251\1\304\2\0\3\251\1\0\1\251\1\0\1\304"+
    "\1\0\1\304\1\251\1\304\1\251\1\304\11\251\1\0"+
    "\7\304\4\251\1\304\13\251\1\304\4\0\2\251\37\0"+
    "\1\203\14\0\1\246\22\0\10\47\1\0\23\47\1\0"+
    "\13\47\1\305\24\47\52\0\1\306\26\0\10\47\1\0"+
    "\23\47\1\0\2\47\1\211\14\47\1\253\20\47\2\0"+
    "\1\47\7\256\1\0\1\47\3\256\1\47\1\256\1\47"+
    "\1\256\1\47\12\256\1\257\3\256\1\47\30\256\4\47"+
    "\2\257\1\0\1\257\1\307\4\257\1\307\2\0\3\257"+
    "\1\0\1\257\1\0\1\307\1\0\1\307\1\257\1\307"+
    "\1\257\1\307\11\257\1\0\7\307\4\257\1\307\13\257"+
    "\1\307\4\0\2\257\37\0\1\213\14\0\1\254\25\0"+
    "\4\310\5\0\1\310\1\0\1\310\6\0\1\310\5\0"+
    "\3\310\20\0\1\310\1\0\1\310\16\0\3\143\4\311"+
    "\1\65\1\0\3\143\1\311\1\143\1\311\6\143\1\311"+
    "\5\143\3\311\20\143\1\311\1\143\1\311\16\143\3\71"+
    "\4\312\2\71\1\152\2\71\1\312\1\153\1\312\6\71"+
    "\1\312\5\71\3\312\20\71\1\312\1\71\1\312\16\71"+
    "\57\0\1\166\72\0\1\313\35\0\1\314\121\0\1\315"+
    "\66\0\1\166\63\0\1\316\77\0\1\270\45\0\1\270"+
    "\100\0\1\166\14\0\6\5\2\0\1\57\3\5\1\0"+
    "\1\5\4\0\1\5\1\0\1\5\1\0\11\5\11\0"+
    "\3\5\1\0\11\5\1\317\1\5\5\0\2\5\1\0"+
    "\6\5\2\0\1\57\3\5\1\0\1\5\4\0\1\5"+
    "\1\0\1\5\1\0\6\5\1\320\2\5\11\0\3\5"+
    "\1\0\13\5\5\0\2\5\1\0\4\5\1\321\1\5"+
    "\2\0\1\57\3\5\1\0\1\5\4\0\1\5\1\0"+
    "\1\5\1\0\11\5\11\0\3\5\1\0\13\5\5\0"+
    "\2\5\1\0\6\5\2\0\1\57\3\5\1\0\1\5"+
    "\4\0\1\5\1\0\1\5\1\0\11\5\11\0\3\5"+
    "\1\0\10\5\1\317\2\5\5\0\2\5\1\0\6\5"+
    "\2\0\1\57\3\5\1\0\1\5\4\0\1\5\1\0"+
    "\1\5\1\0\2\317\7\5\11\0\3\5\1\0\13\5"+
    "\5\0\2\5\1\0\6\5\2\0\1\57\3\5\1\0"+
    "\1\5\4\0\1\5\1\0\1\5\1\0\2\322\7\5"+
    "\11\0\3\5\1\0\13\5\5\0\2\5\10\36\1\0"+
    "\11\36\1\0\11\36\1\0\13\36\1\250\24\36\52\0"+
    "\1\251\26\0\10\47\1\0\23\47\1\0\13\47\1\256"+
    "\24\47\52\0\1\257\31\0\4\5\5\0\1\5\1\0"+
    "\1\5\6\0\1\5\5\0\3\5\20\0\1\5\1\0"+
    "\1\5\16\0\3\143\4\64\1\65\1\0\3\143\1\64"+
    "\1\143\1\64\6\143\1\64\5\143\3\64\20\143\1\64"+
    "\1\143\1\64\16\143\3\71\4\13\2\71\1\152\2\71"+
    "\1\13\1\153\1\13\6\71\1\13\5\71\3\13\20\71"+
    "\1\13\1\71\1\13\16\71\33\0\2\166\77\0\1\323"+
    "\46\0\1\324\151\0\1\270\17\0\6\5\2\0\1\57"+
    "\1\325\2\5\1\0\1\5\4\0\1\5\1\0\1\5"+
    "\1\0\11\5\11\0\3\5\1\0\13\5\5\0\2\5"+
    "\1\0\6\5\2\0\1\57\3\5\1\0\1\5\4\0"+
    "\1\5\1\0\1\5\1\0\10\5\1\326\11\0\3\5"+
    "\1\0\13\5\5\0\2\5\1\0\6\5\2\0\1\57"+
    "\3\5\1\0\1\5\4\0\1\5\1\0\1\5\1\0"+
    "\11\5\11\0\3\5\1\0\2\5\1\327\10\5\5\0"+
    "\2\5\27\0\2\270\117\0\1\166\23\0\1\166\2\0"+
    "\6\5\2\0\1\57\3\5\1\0\1\5\4\0\1\5"+
    "\1\0\1\5\1\0\2\330\7\5\11\0\3\5\1\0"+
    "\13\5\5\0\2\5\1\0\6\5\2\0\1\57\3\5"+
    "\1\0\1\5\4\0\1\5\1\0\1\5\1\0\10\5"+
    "\1\331\11\0\3\5\1\0\13\5\5\0\2\5\1\0"+
    "\6\5\2\0\1\57\3\5\1\0\1\5\4\0\1\5"+
    "\1\0\1\5\1\0\6\5\1\332\2\5\11\0\3\5"+
    "\1\0\13\5\5\0\2\5\1\0\6\5\2\0\1\57"+
    "\3\5\1\0\1\5\4\0\1\5\1\0\1\5\1\0"+
    "\11\5\11\0\2\5\1\333\1\0\13\5\5\0\2\5"+
    "\1\0\6\5\2\0\1\57\1\5\1\334\1\5\1\0"+
    "\1\5\4\0\1\5\1\0\1\5\1\0\11\5\11\0"+
    "\3\5\1\0\4\5\1\334\6\5\5\0\2\5\1\0"+
    "\6\5\2\0\1\57\3\5\1\0\1\5\4\0\1\5"+
    "\1\0\1\5\1\0\11\5\11\0\3\5\1\0\10\5"+
    "\1\335\2\5\5\0\2\5\1\0\6\5\2\0\1\57"+
    "\3\5\1\0\1\5\4\0\1\5\1\0\1\5\1\0"+
    "\7\5\1\336\1\5\11\0\3\5\1\0\13\5\5\0"+
    "\2\5\1\0\6\5\2\0\1\57\3\5\1\0\1\5"+
    "\4\0\1\5\1\0\1\5\1\0\6\5\1\337\2\5"+
    "\11\0\3\5\1\0\13\5\5\0\2\5\1\0\6\5"+
    "\2\0\1\57\3\5\1\0\1\5\4\0\1\5\1\0"+
    "\1\331\1\0\11\5\11\0\3\5\1\0\13\5\5\0"+
    "\2\5\1\0\6\5\2\0\1\57\3\5\1\0\1\5"+
    "\4\0\1\5\1\0\1\5\1\0\10\5\1\322\11\0"+
    "\3\5\1\0\13\5\5\0\2\5\1\0\6\5\2\0"+
    "\1\57\3\5\1\0\1\5\4\0\1\5\1\0\1\5"+
    "\1\0\11\5\11\0\3\5\1\0\7\5\1\340\3\5"+
    "\5\0\2\5\1\0\6\5\2\0\1\57\3\5\1\0"+
    "\1\5\4\0\1\5\1\0\1\322\1\0\11\5\11\0"+
    "\3\5\1\0\13\5\5\0\2\5";

  private static int [] zzUnpackTrans() {
    int [] result = new int[12852];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\4\1\1\11\13\1\2\11\10\1\1\11"+
    "\10\1\1\11\6\1\1\0\1\1\1\0\3\1\1\11"+
    "\1\0\4\1\1\11\1\0\2\11\7\0\10\1\1\11"+
    "\2\1\2\0\2\1\2\0\2\1\2\0\2\1\3\0"+
    "\1\1\1\0\2\1\1\11\6\1\1\11\1\1\11\0"+
    "\1\11\2\0\12\1\2\0\2\1\2\0\2\1\2\0"+
    "\2\1\3\0\2\1\11\0\11\1\1\0\3\1\1\0"+
    "\1\1\1\0\3\1\2\0\2\1\1\0\1\1\6\0"+
    "\7\1\2\0\1\1\3\0\2\1\4\0\4\1\2\0"+
    "\14\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[224];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /* user code: */


	/**
	 * Constructor.  This must be here because JFlex does not generate a
	 * no-parameter constructor.
	 */
	public VelocityTokenMaker() {
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 * @see #addToken(int, int, int)
	 */
	private void addHyperlinkToken(int start, int end, int tokenType) {
		int so = start + offsetShift;
		addToken(zzBuffer, start,end, tokenType, so, true);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 */
	private void addToken(int tokenType) {
		addToken(zzStartRead, zzMarkedPos-1, tokenType);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param tokenType The token's type.
	 * @see #addHyperlinkToken(int, int, int)
	 */
	private void addToken(int start, int end, int tokenType) {
		int so = start + offsetShift;
		addToken(zzBuffer, start,end, tokenType, so, false);
	}


	/**
	 * Adds the token specified to the current linked list of tokens.
	 *
	 * @param array The character array.
	 * @param start The starting offset in the array.
	 * @param end The ending offset in the array.
	 * @param tokenType The token's type.
	 * @param startOffset The offset in the document at which this token
	 *        occurs.
	 * @param hyperlink Whether this token is a hyperlink.
	 */
	public void addToken(char[] array, int start, int end, int tokenType,
						int startOffset, boolean hyperlink) {
		super.addToken(array, start,end, tokenType, startOffset, hyperlink);
		zzStartRead = zzMarkedPos;
	}


	/**
	 * {@inheritDoc}
	 */
	public String[] getLineCommentStartAndEnd(int languageIndex) {
		return new String[] { "##", null };
	}


	/**
	 * Returns the first token in the linked list of tokens generated
	 * from <code>text</code>.  This method must be implemented by
	 * subclasses so they can correctly implement syntax highlighting.
	 *
	 * @param text The text from which to get tokens.
	 * @param initialTokenType The token type we should start with.
	 * @param startOffset The offset into the document at which
	 *        <code>text</code> starts.
	 * @return The first <code>Token</code> in a linked list representing
	 *         the syntax highlighted text.
	 */
	public Token getTokenList(Segment text, int initialTokenType, int startOffset) {

		resetTokenList();
		this.offsetShift = -text.offset + startOffset;

		// Start off in the proper state.
		int state = Token.NULL;
		switch (initialTokenType) {
						case Token.COMMENT_MULTILINE:
				state = MLC;
				start = text.offset;
				break;

			/* No documentation comments */
			default:
				state = Token.NULL;
		}

		s = text;
		try {
			yyreset(zzReader);
			yybegin(state);
			return yylex();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return new TokenImpl();
		}

	}


	/**
	 * Refills the input buffer.
	 *
	 * @return      <code>true</code> if EOF was reached, otherwise
	 *              <code>false</code>.
	 */
	private boolean zzRefill() {
		return zzCurrentPos>=s.offset+s.count;
	}


	/**
	 * Resets the scanner to read from a new input stream.
	 * Does not close the old reader.
	 *
	 * All internal variables are reset, the old input stream 
	 * <b>cannot</b> be reused (internal buffer is discarded and lost).
	 * Lexical state is set to <tt>YY_INITIAL</tt>.
	 *
	 * @param reader   the new input stream 
	 */
	public final void yyreset(Reader reader) {
		// 's' has been updated.
		zzBuffer = s.array;
		/*
		 * We replaced the line below with the two below it because zzRefill
		 * no longer "refills" the buffer (since the way we do it, it's always
		 * "full" the first time through, since it points to the segment's
		 * array).  So, we assign zzEndRead here.
		 */
		//zzStartRead = zzEndRead = s.offset;
		zzStartRead = s.offset;
		zzEndRead = zzStartRead + s.count - 1;
		zzCurrentPos = zzMarkedPos = zzPushbackPos = s.offset;
		zzLexicalState = YYINITIAL;
		zzReader = reader;
		zzAtBOL  = true;
		zzAtEOF  = false;
	}




  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public VelocityTokenMaker(Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public VelocityTokenMaker(InputStream in) {
    this(new InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 192) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Closes the input stream.
   */
  public final void yyclose() throws IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   IOException  if any I/O-Error occurs
   */
  public Token yylex() throws IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 4: 
          { addNullToken(); return firstToken;
          }
        case 27: break;
        case 20: 
          { addToken(Token.LITERAL_CHAR);
          }
        case 28: break;
        case 16: 
          { start = zzMarkedPos-2; yybegin(MLC);
          }
        case 29: break;
        case 6: 
          { addToken(Token.WHITESPACE);
          }
        case 30: break;
        case 19: 
          { addToken(Token.LITERAL_NUMBER_HEXADECIMAL);
          }
        case 31: break;
        case 21: 
          { addToken(Token.ERROR_STRING_DOUBLE);
          }
        case 32: break;
        case 17: 
          { addToken(Token.LITERAL_NUMBER_FLOAT);
          }
        case 33: break;
        case 22: 
          { addToken(Token.RESERVED_WORD);
          }
        case 34: break;
        case 8: 
          { addToken(Token.SEPARATOR);
          }
        case 35: break;
        case 1: 
          { addToken(Token.IDENTIFIER);
          }
        case 36: break;
        case 11: 
          { addToken(start,zzStartRead-1, Token.COMMENT_EOL); addNullToken(); return firstToken;
          }
        case 37: break;
        case 15: 
          { start = zzMarkedPos-2; yybegin(EOL_COMMENT);
          }
        case 38: break;
        case 26: 
          { addToken(Token.FUNCTION);
          }
        case 39: break;
        case 3: 
          { addToken(Token.ERROR_CHAR); addNullToken(); return firstToken;
          }
        case 40: break;
        case 5: 
          { addToken(Token.ERROR_STRING_DOUBLE); addNullToken(); return firstToken;
          }
        case 41: break;
        case 18: 
          { yybegin(YYINITIAL); addToken(start,zzStartRead+2-1, Token.COMMENT_MULTILINE);
          }
        case 42: break;
        case 13: 
          { addToken(Token.ERROR_CHAR);
          }
        case 43: break;
        case 23: 
          { addToken(Token.LITERAL_BOOLEAN);
          }
        case 44: break;
        case 14: 
          { addToken(Token.LITERAL_STRING_DOUBLE_QUOTE);
          }
        case 45: break;
        case 25: 
          { int temp=zzStartRead; addToken(start,zzStartRead-1, Token.COMMENT_EOL); addHyperlinkToken(temp,zzMarkedPos-1, Token.COMMENT_EOL); start = zzMarkedPos;
          }
        case 46: break;
        case 24: 
          { int temp=zzStartRead; addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); addHyperlinkToken(temp,zzMarkedPos-1, Token.COMMENT_MULTILINE); start = zzMarkedPos;
          }
        case 47: break;
        case 12: 
          { addToken(Token.ERROR_NUMBER_FORMAT);
          }
        case 48: break;
        case 2: 
          { addToken(Token.LITERAL_NUMBER_DECIMAL_INT);
          }
        case 49: break;
        case 7: 
          { addToken(Token.OPERATOR);
          }
        case 50: break;
        case 9: 
          { 
          }
        case 51: break;
        case 10: 
          { addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
          }
        case 52: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            switch (zzLexicalState) {
            case EOL_COMMENT: {
              addToken(start,zzStartRead-1, Token.COMMENT_EOL); addNullToken(); return firstToken;
            }
            case 225: break;
            case YYINITIAL: {
              addNullToken(); return firstToken;
            }
            case 226: break;
            case MLC: {
              addToken(start,zzStartRead-1, Token.COMMENT_MULTILINE); return firstToken;
            }
            case 227: break;
            default:
            return null;
            }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
