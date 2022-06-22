#include <jni.h>
JNIEXPORT jstring JNICALL
Java_com_hh_baselibrary_util_SM4ConstantsUtil_getSM4SecretKey(JNIEnv *env, jobject instance) {
    char *str="80a5ab47c28f48e5";
    return (*env)->NewStringUTF(env, str);
}

JNIEXPORT jstring JNICALL
Java_com_hh_baselibrary_util_SM4ConstantsUtil_getSM4IV(JNIEnv *env, jobject instance) {
char *str="96c1ab47d551091a";
return (*env)->NewStringUTF(env, str);
}