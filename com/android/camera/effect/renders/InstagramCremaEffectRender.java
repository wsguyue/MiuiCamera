package com.android.camera.effect.renders;

import com.android.gallery3d.ui.GLCanvas;
import java.nio.IntBuffer;

public class InstagramCremaEffectRender extends RGBTransEffectRender {
    private static final int[] sCurveBLut = new int[]{20, 20, 21, 22, 23, 23, 24, 25, 26, 26, 27, 28, 29, 29, 30, 31, 32, 33, 33, 34, 35, 36, 36, 37, 38, 39, 39, 40, 41, 42, 42, 43, 44, 45, 45, 46, 47, 48, 49, 49, 50, 51, 52, 52, 53, 54, 55, 55, 56, 57, 58, 58, 59, 60, 61, 61, 62, 63, 64, 64, 65, 66, 67, 67, 68, 69, 70, 70, 71, 72, 72, 73, 74, 75, 75, 76, 77, 78, 78, 79, 80, 81, 81, 82, 83, 83, 84, 85, 86, 86, 87, 88, 89, 89, 90, 91, 92, 92, 93, 94, 94, 95, 96, 97, 97, 98, 99, 100, 100, 101, 102, 103, 103, 104, 105, 106, 106, 107, 108, 109, 109, 110, 111, 112, 112, 113, 114, 115, 116, 116, 117, 118, 119, 119, 120, 121, 122, 123, 123, 124, 125, 126, 126, 127, 128, 129, 130, 130, 131, 132, 133, 134, 134, 135, 136, 137, 138, 138, 139, 140, 141, 142, 142, 143, 144, 145, 146, 146, 147, 148, 149, 150, 150, 151, 152, 153, 154, 154, 155, 156, 157, 158, 158, 159, 160, 161, 162, 162, 163, 164, 165, 166, 166, 167, 168, 169, 169, 170, 171, 172, 172, 173, 174, 175, 176, 176, 177, 178, 179, 179, 180, 181, 182, 182, 183, 184, 185, 185, 186, 187, 188, 188, 189, 190, 191, 191, 192, 193, 194, 194, 195, 196, 197, 197, 198, 199, 200, 200, 201, 202, 202, 203, 204, 205, 205, 206, 207, 208, 208, 209, 210, 211, 211, 212, 213, 214};
    private static final int[] sCurveGLut = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255};
    private static IntBuffer sCurveRGBLutBuffer;
    private static final int[] sCurveRLut = new int[]{4, 5, 6, 7, 9, 10, 11, 12, 14, 15, 16, 17, 19, 20, 21, 22, 24, 25, 26, 27, 28, 30, 31, 32, 33, 35, 36, 37, 38, 40, 41, 42, 43, 45, 46, 47, 48, 50, 51, 52, 53, 54, 56, 57, 58, 59, 60, 62, 63, 64, 65, 67, 68, 69, 70, 71, 73, 74, 75, 76, 77, 78, 80, 81, 82, 83, 84, 85, 87, 88, 89, 90, 91, 92, 94, 95, 96, 97, 98, 99, 100, 102, 103, 104, 105, 106, 107, 108, 109, 110, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 179, 180, 181, 182, 183, 184, 185, 186, 186, 187, 188, 189, 190, 191, 191, 192, 193, 194, 195, 196, 197, 197, 198, 199, 200, 201, 201, 202, 203, 204, 205, 206, 206, 207, 208, 209, 210, 210, 211, 212, 213, 214, 214, 215, 216, 217, 217, 218, 219, 220, 220, 221, 222, 223, 224, 224, 225, 226, 227, 227, 228, 229, 230, 230, 231, 232, 233, 233, 234, 235, 236, 236, 237, 238, 238, 239, 240, 241, 241, 242, 243, 244, 244, 245, 246, 247, 247, 248, 249, 249, 250, 251, 252, 252, 253, 254, 255};
    private static final int[] sSelfRGBLut = new int[]{20, 20, 21, 22, 23, 23, 24, 25, 26, 26, 27, 28, 29, 29, 30, 31, 32, 33, 33, 34, 35, 36, 36, 37, 38, 39, 39, 40, 41, 42, 42, 43, 44, 45, 45, 46, 47, 48, 49, 49, 50, 51, 52, 52, 53, 54, 55, 55, 56, 57, 58, 58, 59, 60, 61, 61, 62, 63, 64, 64, 65, 66, 67, 67, 68, 69, 70, 70, 71, 72, 72, 73, 74, 75, 75, 76, 77, 78, 78, 79, 80, 81, 81, 82, 83, 83, 84, 85, 86, 86, 87, 88, 89, 89, 90, 91, 92, 92, 93, 94, 94, 95, 96, 97, 97, 98, 99, 100, 100, 101, 102, 103, 103, 104, 105, 106, 106, 107, 108, 109, 109, 110, 111, 112, 112, 113, 114, 115, 116, 116, 117, 118, 119, 119, 120, 121, 122, 123, 123, 124, 125, 126, 126, 127, 128, 129, 130, 130, 131, 132, 133, 134, 134, 135, 136, 137, 138, 138, 139, 140, 141, 142, 142, 143, 144, 145, 146, 146, 147, 148, 149, 150, 150, 151, 152, 153, 154, 154, 155, 156, 157, 158, 158, 159, 160, 161, 162, 162, 163, 164, 165, 166, 166, 167, 168, 169, 169, 170, 171, 172, 172, 173, 174, 175, 176, 176, 177, 178, 179, 179, 180, 181, 182, 182, 183, 184, 185, 185, 186, 187, 188, 188, 189, 190, 191, 191, 192, 193, 194, 194, 195, 196, 197, 197, 198, 199, 200, 200, 201, 202, 202, 203, 204, 205, 205, 206, 207, 208, 208, 209, 210, 211, 211, 212, 213, 214};
    private static IntBuffer sSelfRGBLutBuffer;

    private static IntBuffer getCurveRGBLutBuffer() {
        if (sCurveRGBLutBuffer == null) {
            int[] rgbLut = new int[sCurveRLut.length];
            for (int i = 0; i < rgbLut.length; i++) {
                rgbLut[i] = (((sCurveBLut[i] << 16) | -16777216) | (sCurveGLut[i] << 8)) | sCurveRLut[i];
            }
            sCurveRGBLutBuffer = IntBuffer.wrap(rgbLut);
        }
        sCurveRGBLutBuffer.rewind();
        return sCurveRGBLutBuffer;
    }

    private static IntBuffer getSelfRGBLutBuffer() {
        if (sSelfRGBLutBuffer == null) {
            for (int i = 0; i < sSelfRGBLut.length; i++) {
                sSelfRGBLut[i] = (((sSelfRGBLut[i] << 16) | -16777216) | (sSelfRGBLut[i] << 8)) | sSelfRGBLut[i];
            }
            sSelfRGBLutBuffer = IntBuffer.wrap(sSelfRGBLut);
        }
        sSelfRGBLutBuffer.rewind();
        return sSelfRGBLutBuffer;
    }

    public static Render create(GLCanvas canvas, int id) {
        CurveEffectRender firstPassRender = new CurveEffectRender(canvas, id);
        firstPassRender.setRGBTransLutBuffer(getCurveRGBLutBuffer());
        InstagramCremaEffectRender secondPassRender = new InstagramCremaEffectRender(canvas, id);
        secondPassRender.setRGBTransLutBuffer(getSelfRGBLutBuffer());
        return new PipeRenderPair(canvas, firstPassRender, secondPassRender, false);
    }

    protected InstagramCremaEffectRender(GLCanvas canvas, int id) {
        super(canvas, id);
    }

    public String getFragShaderString() {
        return "precision mediump int; \nprecision mediump float; \nuniform sampler2D sTexture; \nuniform sampler2D sRGBLut; \nvarying vec2 vTexCoord; \nuniform float uAlpha; \nvec3 get_max_min_mid(float r, float g, float b) { \n    float max_d = max(max(r, g), b); \n    float min_d = min(min(r, g), b); \n    float mid_d = r + g + b - max_d - min_d; \n    return vec3(max_d, mid_d, min_d); \n} \nfloat get_H(float r, float g, float b, float max_rgb, float delta) { \n    float H = 0.0, ff = 60.0 / delta; \n    if (r == max_rgb) { \n        H = (g - b) * ff; \n    } else if (g == max_rgb) { \n        H = (b - r) * ff + 120.0; \n    } else { \n        H = (r - g) * ff + 240.0; \n    } \n    return H; \n} \nvec2 swap_data(vec2 f_data) { \n    float tmp = f_data.s; \n    f_data.s = f_data.t; \n    f_data.t = tmp; \n    return f_data; \n} \nvec4 tune_hue_(vec4 color, float h_values, float H) { \n    if (h_values == 0.0) { \n        return color; \n    } else { \n        vec3 max_min_v = get_max_min_mid(color.r, color.g, color.b); \n        float rr = max_min_v.r, bb = max_min_v.b, gg = 0.0; \n        float HH = H + h_values, LL = (rr + bb) / 2.0, Lnum = (LL - 0.5) * 255.0, SS; \n        if (HH < 0.0) { \n            HH = HH + 360.0; \n        } \n        if (HH > 360.0) { \n            HH = HH - 360.0; \n        } \n        if (LL < 0.5) { \n            SS = (rr - bb) / (rr + bb); \n        } else { \n            SS = (rr - bb) / (2.0 - (rr + bb)); \n        } \n        int H_index = int(HH / 60.0); \n        float extra = HH - float(H_index * 60); \n        if ((int(float(H_index) / 2.0) * 2) != H_index) { \n            extra = 60.0 - extra; \n        } \n        extra = (extra * 255.0 + 32.0) / 60.0; \n        gg = extra - (extra - 128.0) * (1.0 - SS); \n        if (Lnum > 0.0) { \n            gg = gg + (((255.0 - gg) * Lnum + 64.0) / 128.0); \n        } else { \n            gg = gg + (gg * Lnum / 128.0); \n        } \n        gg = gg / 255.0; \n        gg = min(max(gg, 0.0), 1.0); \n        if (H_index == 1) { \n            vec2 f_data = swap_data(vec2(rr, gg)); \n            rr = f_data.s; \n            gg = f_data.t; \n        } else if (H_index == 2) { \n            vec2 f_data = swap_data(vec2(rr, bb)); \n            rr = f_data.s; \n            bb = f_data.t; \n            f_data = swap_data(vec2(gg, bb)); \n            gg = f_data.s; \n            bb = f_data.t; \n        } else if (H_index == 3) { \n            vec2 f_data = swap_data(vec2(rr, bb)); \n            rr = f_data.s; \n            bb = f_data.t; \n        } else if (H_index == 4) { \n            vec2 f_data = swap_data(vec2(rr, gg)); \n            rr = f_data.s; \n            gg = f_data.t; \n            f_data = swap_data(vec2(gg, bb)); \n            gg = f_data.s; \n            bb = f_data.t; \n        } else if (H_index == 5) { \n            vec2 f_data = swap_data(vec2(gg, bb)); \n            gg = f_data.s; \n            bb = f_data.t; \n        } \n        return vec4(rr, gg, bb, 1.0); \n    } \n} \nvec4 tune_s_(vec4 color, float sValue) { \n    if (sValue == 0.0) { \n        return color; \n    } \n    vec3 max_min_v = get_max_min_mid(color.r, color.g, color.b); \n    float LL = (max_min_v.r + max_min_v.b) / 2.0, SS = 0.0, S_data = 0.0; \n    if (LL < 0.5) { \n        SS = (max_min_v.r - max_min_v.b) / (max_min_v.r + max_min_v.b); \n    } else { \n        SS = (max_min_v.r - max_min_v.b) / (2.0 - (max_min_v.r + max_min_v.b)); \n    } \n    S_data = sValue; \n    if (sValue > 0.0) { \n        float S1 = SS + sValue; \n        if (S1 > 1.0) { \n            S1 = SS; \n        } else { \n            S1 = 1.0 - sValue; \n        } \n        S_data = 1.0 / S1 - 1.0; \n    } \n    vec4 dst_color; \n    dst_color = color + (color - LL) * S_data; \n    dst_color = clamp(dst_color, 0.0, 1.0); \n    return dst_color; \n} \nvec4 tune_b_(vec4 color, float bValue, float max_v, float min_v) { \n    if (bValue == 0.0) { \n        return color; \n    } else { \n        vec4 rgb_color; \n        if (bValue > 0.0) { \n            rgb_color = color + (max_v - color) * bValue; \n        } else { \n            rgb_color = color + (color - min_v) * bValue; \n        } \n        rgb_color = clamp(rgb_color, 0.0, 1.0); \n        return rgb_color; \n    } \n} \nvec4 tuneProcess(vec4 color, vec3 prames1, vec3 prames2, vec3 prames3, vec3 prames4) { \n    vec4 dst_color; \n    float v_values = prames1.b; \n    if (v_values == 0.0) { \n        dst_color = color; \n    } else { \n        if (v_values > 0.0) { \n            dst_color = color + (1.0 - color) * v_values; \n        } else { \n            dst_color = color + color * v_values; \n        } \n    } \n    // return dst_color; \n    dst_color.a = 1.0; \n    color = dst_color; \n    float r = float(int(color.r * 255.0)) / 255.0, g = float(int(color.g * 255.0)) / 255.0, b = float(int(color.b * 255.0)) / 255.0; \n    vec3 max_min_v = get_max_min_mid(r, g, b); \n    float dst_r = r, dst_g = g, dst_b = b; \n    float delta = (max_min_v.r - max_min_v.b), entire = (max_min_v.r + max_min_v.b); \n    float L = entire / 2.0; \n    if (delta == 0.0) { \n        return color; \n    } else { \n        float H = get_H(r, g, b, max_min_v.r, delta); \n        float h_values = 0.0, s_values = 0.0, v_values = 0.0, f_ratio = 0.0; \n        vec3 hsv_values = vec3(0.0, 0.0, 0.0); \n        float HH = H; \n        if (HH < 0.0) { \n            HH = HH + 360.0; \n        } \n        if (HH > 360.0) { \n            HH = HH - 360.0; \n        } \n        if (!((HH > 315.0 || HH < 105.0) || (HH > 135.0 && HH < 225.0))) { \n            hsv_values.r = prames1.r; \n            hsv_values.g = prames1.g; \n            hsv_values.b = 0.0; \n        } else { \n            if (HH > 315.0 && HH < 345.0) { \n                f_ratio = (HH - 315.0) / 30.0; \n                hsv_values = prames2 * f_ratio; \n            } else if (HH >= 345.0 || HH <= 15.0) { \n                f_ratio = 1.0; \n                hsv_values = prames2 * f_ratio; \n            } else if (HH > 15.0 && HH < 45.0) { \n                f_ratio = (45.0 - HH) / 30.0; \n                hsv_values = prames2 * f_ratio + prames3 * (1.0 - f_ratio); \n            } else if (HH >= 45.0 && HH <= 75.0) { \n                f_ratio = 1.0; \n                hsv_values = prames3 * f_ratio; \n            } else if (HH > 75.0 && HH < 105.0) { \n                f_ratio = (105.0 - HH) / 30.0; \n                hsv_values = prames3 * f_ratio; \n            } else if (HH > 135.0 && HH < 165.0) { \n                f_ratio = (HH - 135.0) / 30.0; \n                hsv_values = prames4 * f_ratio; \n            } else if (HH >= 165.0 && HH <= 195.0) { \n                f_ratio = 1.0; \n                hsv_values = prames4 * f_ratio; \n            } else if (HH > 195.0 && HH < 225.0) { \n                f_ratio = (225.0 - HH) / 30.0; \n                hsv_values = prames4 * f_ratio; \n            } \n            hsv_values.r = hsv_values.r + prames1.r; \n            hsv_values.g = hsv_values.g + prames1.g; \n        } \n        v_values = hsv_values.b; \n        vec4 dst_color; \n        dst_color = tune_b_(color, v_values, max_min_v.r, max_min_v.b); \n        h_values = hsv_values.r; \n        dst_color = tune_hue_(dst_color, h_values, H); \n        s_values = hsv_values.g; \n        dst_color = tune_s_(dst_color, s_values); \n        return dst_color; \n    } \n} \nvec4 adjust_contrast(vec4 color, float f_contrast) { \n    float ff = log(1.0 - f_contrast) / log(0.5); \n    float r = color.r, g = color.g, b = color.b; \n    if (r < 0.5) { \n        r = pow(2.0 * r, ff) / 2.0; \n    } else { \n        r = 1.0 - pow(2.0 * (1.0 - r), ff) / 2.0; \n    } \n    if (g < 0.5) { \n        g = pow(2.0 * g, ff) / 2.0; \n    } else { \n        g = 1.0 - pow(2.0 * (1.0 - g), ff) / 2.0; \n    } \n    if (b < 0.5) { \n        b = pow(2.0 * b, ff) / 2.0; \n    } else { \n        b = 1.0 - pow(2.0 * (1.0 - b), ff) / 2.0; \n    } \n    return vec4(r, g, b, 1.0); \n} \nvec4 adjust_light(vec4 color, float f_light, float ff_ll) { \n    color.r = pow(color.r, ff_ll); \n    color.r = (color.r * f_light) / (2.0 * color.r * f_light - color.r - f_light + 1.0); \n    color.r = pow(color.r, 1.0 / ff_ll); \n    color.g = pow(color.g, ff_ll); \n    color.g = (color.g * f_light) / (2.0 * color.g * f_light - color.g - f_light + 1.0); \n    color.g = pow(color.g, 1.0 / ff_ll); \n    color.b = pow(color.b, ff_ll); \n    color.b = (color.b * f_light) / (2.0 * color.b * f_light - color.b - f_light + 1.0); \n    color.b = pow(color.b, 1.0 / ff_ll); \n    color = clamp(color, 0.0, 1.0); \n    return color; \n} \nvoid main() { \n    vec4 color = texture2D(sTexture, vTexCoord); \n    int index = int(color.r * 255.0); \n    float index1 = float(index) / 256.0; \n    color.r = texture2D(sRGBLut, vec2(index1, 0.0)).r; \n    index = int(color.g * 255.0); \n    index1 = float(index) / 256.0; \n    color.g = texture2D(sRGBLut, vec2(index1, 0.0)).r; \n    index = int(color.b * 255.0); \n    index1 = float(index) / 256.0; \n    color.b = texture2D(sRGBLut, vec2(index1, 0.0)).r; \n    color = clamp(color, 0.0, 1.0); \n    vec3 Prames1 = vec3(0.0, -0.22, -0.02); \n    vec3 Prames2 = vec3(0.0, -0.34, -0.07); \n    vec3 Prames3 = vec3(0.0,  0.0,  -0.21); \n    vec3 Prames4 = vec3(5.0,  0.43, -0.58); \n    color = tuneProcess(color, Prames1, Prames2, Prames3, Prames4); \n    float bri = 0.5 + 23.0 / 155.0; \n    float ff_ll = 3.75; \n    color = adjust_light(color, bri, ff_ll); \n    color = adjust_contrast(color, 0.665); \n    gl_FragColor = clamp(color, 0.0, 1.0)*uAlpha; \n}";
    }
}
